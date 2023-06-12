package per.study.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class FileChannelTransfer {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("/Users/Lrwei/Lrwei/Java/NettyLearning/NIO/wp.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/Lrwei/Lrwei/Java/NettyLearning/NIO/wp.copy.jpg");

        FileChannel sourceCh = fileInputStream.getChannel();
        FileChannel destCh = fileOutputStream.getChannel();

        destCh.transferFrom(sourceCh, 0, sourceCh.size());

        sourceCh.close();
        destCh.close();

        fileInputStream.close();
        fileOutputStream.close();
    }
}
