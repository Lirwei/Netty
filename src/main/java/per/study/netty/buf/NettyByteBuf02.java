package per.study.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/6
 **/
public class NettyByteBuf02 {
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.copiedBuffer("hello, world", CharsetUtil.UTF_8);
        if (buffer.hasArray()) {
            byte[] content = buffer.array();
            System.out.println(new String(content, 0, buffer.writerIndex(), CharsetUtil.UTF_8));

            System.out.println("buffer: " + buffer);
            System.out.println(buffer.arrayOffset());
            System.out.println(buffer.readerIndex());
            System.out.println(buffer.writerIndex());
            System.out.println(buffer.capacity());

//            System.out.println(buffer.readByte());
            System.out.println(buffer.getByte(0)); // 104
            System.out.println(buffer.readableBytes()); // 可读的子节数

            // 遍历
            System.out.println("content: ");
            for (int i = 0; i < buffer.readableBytes(); i++) {
                System.out.println((char) buffer.getByte(i));
            }

            // 按某个范围读取
            System.out.println(buffer.getCharSequence(0, 4, CharsetUtil.UTF_8));
            System.out.println(buffer.getCharSequence(4, 6, CharsetUtil.UTF_8));
        }
    }
}
