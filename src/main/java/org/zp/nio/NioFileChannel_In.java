package org.zp.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO FileInputStream FileChannel 方法
 *
 * @author Zhang
 * @date 2023/09/26 14:55:29
 */
public class NioFileChannel_In {
    public static void main(String[] args) throws Exception{
        File file = new File("text.txt");

        //创建输出流
        FileInputStream fileInputStream = new FileInputStream(file);

        //获取Channel信道
        FileChannel channel = fileInputStream.getChannel();

        //创建Buffer读取输入流中的数据
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //读取byteBuffer中的字节数据
        channel.read(byteBuffer);

        //将byteBuffer的字节数据转换为String类型
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
}
