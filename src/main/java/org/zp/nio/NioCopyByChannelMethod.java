package org.zp.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * 蔚来按渠道法复制
 *
 * @author Zhang
 * @date 2023/09/28 14:33:19
 */
public class NioCopyByChannelMethod {

    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("text.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("textMethod.txt");

        FileChannel sourceCh = fileInputStream.getChannel();
        FileChannel destCh = fileOutputStream.getChannel();

        destCh.transferFrom(sourceCh,0,sourceCh.size());
        sourceCh.close();
        destCh.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
