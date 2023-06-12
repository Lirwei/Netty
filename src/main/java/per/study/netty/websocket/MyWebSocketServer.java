package per.study.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/6
 **/
public class MyWebSocketServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)) // 在bossGroup增加一个日志处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    // 因为基于Http协议，使用http的编码和解码器
                                    .addLast(new HttpServerCodec())
                                    // 以块的方式写，添加ChunkedWriteHandler处理器
                                    .addLast(new ChunkedWriteHandler())
                                    /**
                                     * 说明
                                     * 1.http数据在传输过程中是分段，HttpObjectAggregator就是将多个段聚合
                                     * 2.当浏览器发送大量数据时，就会发出多次http请求
                                     */
                                    .addLast(new HttpObjectAggregator(8192))
                                    /**
                                     * 说明
                                     * 1.对应websocket，他的数据以帧（frame）的形式传递
                                     * 2.WebSocketFrame 有多个子类
                                     * 3.浏览器请求 ws://localhost:8080/xxx [xxx]表示请求的url
                                     * 4.WebSocketServerProtocolHandler 核心功能是将Http协议升级为ws协议，保持长连接
                                     * 5.101状态码
                                     */
                                    .addLast(new WebSocketServerProtocolHandler("/xxx"))
                                    // 自定义handler
                                    .addLast(new MyTextWebSocketFrameHandler())
                            ;
                        }
                    })
            ;
            ChannelFuture future = bootstrap.bind(8080).sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
