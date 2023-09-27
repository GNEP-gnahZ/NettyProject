package org.zp.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO FileOutputStream FileChannel 方法
 *
 * @author Zhang
 * @date 2023/09/26 14:07:13
 */
public class NioFileChannel_Out {

    public static void main(String[] args) throws Exception {
        String str = "Hello Nio";

//        创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("text.txt");

        //通过 fileOutputStream获取对应的FileChannel
//        这个fileChannel 真实的类型是 FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

//        创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将字符串字节数据放入byteBuffer中  可写状态
        byteBuffer.put(str.getBytes());

        //对ByteBuffer进行flip 可读状态
        byteBuffer.flip();

//        将byteBuffer数据写入到 fileChannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }
}
