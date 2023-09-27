package org.zp.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO 文件通道复制
 *
 * @author Zhang
 * @date 2023/09/26 15:00:24
 */
// Situation 1
public class NioFileChannel_Copy {


    public static void main(String[] args) throws Exception{
        File file = new File("text.txt");

        FileInputStream fileInputStream = new FileInputStream(file);

        FileChannel inChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //将输入流通道的数据写入ByteBuffer 写入后position位置为文件大小的位置
        inChannel.read(byteBuffer);

        //反转初始化position的值为0供下一个通道使用
        byteBuffer.flip();
        FileOutputStream fileOutputStream = new FileOutputStream("newText.txt");

        FileChannel outChannel = fileOutputStream.getChannel();

        //将byteBuffer的值写入到通道当中
        outChannel.write(byteBuffer);
        fileInputStream.close();
        fileOutputStream.close();
    }
}

/**
 * NIO 文件通道复制流
 *
 * @author Zhang
 * @date 2023/09/26 15:30:29
 */
// Situation 2
class  NioFileChannel_Copy_Stream {

    public static void main(String[] args) throws Exception{

        FileInputStream fileInputStream = new FileInputStream("text.txt");
        FileChannel inChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("newText.txt");
        FileChannel outChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("--------------");
        while (true){

            //此处如果不clear()会导致byteBuffer中position的值和limit的值相同 read为0一直读取
            byteBuffer.clear();
            //将输入流通道的数据写入ByteBuffer 写入后position位置为文件大小的位置
            int read = inChannel.read(byteBuffer);
            System.out.println("read= " + read);
            if (read == -1){
                break;
            }
            //反转初始化position的值为0供下一个通道使用
            byteBuffer.flip();

            //将byteBuffer的值写入到通道当中
            outChannel.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}