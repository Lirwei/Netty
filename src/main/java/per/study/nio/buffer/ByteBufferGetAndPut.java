package per.study.nio.buffer;

import java.nio.ByteBuffer;

public class ByteBufferGetAndPut {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(100);
        buffer.putLong(9);
        buffer.putChar('李');
        buffer.putShort((short) 4);

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
    }
}
