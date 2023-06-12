package per.study.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {

    public static void main(String[] args) throws IOException {
        // 线程池机制
        // 1.创建一个线程池
        // 2.如果有客户端连接，就创建一个线程，与之通讯（单独写一个方法）
        ExecutorService threadPool = Executors.newCachedThreadPool();

        // 3.创建 ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("server start...");

        while (true) {
            // 监听，等待客户端连接
            final Socket socket = serverSocket.accept();
            System.out.println("connect one client...");

            // 创建一个线程，与之通讯（单独写一个方法）
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    // 可以与客户端通讯
                    handler(socket);
                }
            });
        }
    }

    public static void handler(Socket socket) {
        try {
            System.out.println("Thread id=[" + Thread.currentThread().getId() + "], name=[" + Thread.currentThread().getName() + "]");
            System.out.println("client port=[" + socket.getPort() + "]");
            byte[] buf = new byte[1024];
            // 通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                // 输出客户端发送的数据
                System.out.println(new String(buf, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("close client....");
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}