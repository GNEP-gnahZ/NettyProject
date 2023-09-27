package org.zp.bio;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 生物服务器
 *
 * @author Zhang
 * @date 2023/09/23 14:52:15
 */
public class BioServer {

    //cmd telnet 127.0.0.1 6666
    //多个cmd请求的线程不一样
    public static void main(String[] args) throws Exception{

        //线程池机制

        //思路
        //1.创建一个线程池
        //2.如果有客户端连接，就创建一个线程，与之通讯

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了");
        while (true){
            //监听，等待客户连接
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            //创建一个线程与之通讯
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }


    public static void handler(Socket socket){
        try {
            System.out.println("线程信息 id=" + Thread.currentThread().getId() + "线程名称" + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            //通过Socket获取输入流
            InputStream inputStream = socket.getInputStream();

            //循环读取客户端发送的数据
            while (true){
                System.out.println("线程信息 id=" + Thread.currentThread().getId() + "线程名称" + Thread.currentThread().getName());
                int read = inputStream.read(bytes);
                if (read!=-1){
                    System.out.println(new String(bytes,0,read));//输出客户端发送的数据
                }else {
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭和client的连接
            System.out.println("关闭和Client的连接");
            try {
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
