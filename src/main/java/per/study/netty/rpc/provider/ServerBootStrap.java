package per.study.netty.rpc.provider;

import per.study.netty.rpc.netty.NettyServer;

// ServerBootStrap 会启动一个服务提供者，就是NettyServer
public class ServerBootStrap {
    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1", 8080);
    }
}
