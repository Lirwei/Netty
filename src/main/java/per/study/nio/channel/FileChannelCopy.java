package per.study.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelCopy {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("/Users/Lrwei/Lrwei/Java/NettyLearning/NIO/hello.txt");
        FileChannel fisChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("/Users/Lrwei/Lrwei/Java/NettyLearning/NIO/hello.copy.txt");
        FileChannel fosChannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        while (true) {
            buffer.clear();
            int read = fisChannel.read(buffer);
            if (read == -1) break;
            buffer.flip();
            fosChannel.write(buffer);
        }

        fisChannel.close();
        fosChannel.close();

        fis.close();
        fos.close();
    }
}
