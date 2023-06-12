package per.study.nio.copy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7001);
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(address);
        String filename = "/Users/Lrwei/Lrwei/Java/Netty/NIO/wp.jpg";

        FileChannel fileChannel = new FileInputStream(filename).getChannel();
        long startTime = System.currentTimeMillis();

        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送总字节数：" + transferCount + "\nTime: " + (System.currentTimeMillis() - startTime));

        fileChannel.close();
        socketChannel.close();
    }
}
