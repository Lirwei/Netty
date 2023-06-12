package per.study.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/6
 **/
public class NettyByteBuf01 {
    public static void main(String[] args) {
        // byte[10]
        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }

        // 在netty 的buffer中，不需要使用flip进行反转，底层维护了readIndex和writeIndex
        // 通过readIndex、writeIndex、capacity，将buffer分成三个区
        // 0 -- readIndex               已经读取的区域
        // readIndex -- writeIndex      可读的区域
        // writeIndex -- capacity       可写的区域
        System.out.println("capacity: " + buffer.capacity());
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.getByte(i));
        }
    }
}
