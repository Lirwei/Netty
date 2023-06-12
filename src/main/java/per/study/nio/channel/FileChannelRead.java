package per.study.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileChannelRead {
    public static void main(String[] args) throws Exception {
        // 1.创建一个输入流
        File file = new File("/Users/Lrwei/Lrwei/Java/NettyLearning/NIO/hello.txt");
        FileInputStream fis = new FileInputStream(file);
        // 2.通过fis获取对应的FileChannel
        FileChannel fileChannel = fis.getChannel();
        // 3.创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        // 4.将fileChannel中的数据写入Buffer
        fileChannel.read(byteBuffer);

//        byteBuffer.flip();
        // 5.将字节转String
        System.out.println(new String(byteBuffer.array(), StandardCharsets.UTF_8));

        fileChannel.close();
        fis.close();
    }
}
