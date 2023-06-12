package per.study.nio.channel;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileChannelWrite {
    public static void main(String[] args) throws Exception {
        String str = "hello, world";
        // 1.创建一个输出流
        FileOutputStream fos = new FileOutputStream("/Users/Lrwei/Lrwei/Java/NettyLearning/NIO/hello.txt");
        // 2.通过fos获取对应的FileChannel
        FileChannel fileChannel = fos.getChannel();
        // 3.创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 4.将str放入byteBuffer
        byteBuffer.put(str.getBytes(StandardCharsets.UTF_8));

        byteBuffer.flip();
        // 5.将byteBuffer写入fileChannel
        fileChannel.write(byteBuffer);

        fileChannel.close();
        fos.close();
    }
}
