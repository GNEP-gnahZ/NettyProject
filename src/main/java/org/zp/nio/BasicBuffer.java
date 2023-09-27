package org.zp.nio;

import java.nio.IntBuffer;

/**
 * 基本缓冲区
 *
 * @author Zhang
 * @date 2023/09/26 10:12:23
 */
public class BasicBuffer {
    public static void main(String[] args) {
        //Ctrl + H 调出层级结构关系
        //举例说明Buffer的使用（简单说明）
        //创建一个Buffer，大小为5，即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(10);

        //write
        for (int i=0;i<intBuffer.capacity();i++){
            intBuffer.put(i*2);
        }

        //*flip方法读写切换
        //flip操作会将position的值赋给limit
        //position的值重置为0
        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
