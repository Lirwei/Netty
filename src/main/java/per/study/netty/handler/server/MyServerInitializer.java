package per.study.netty.handler.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import per.study.netty.handler.codec.MyByteToLongDecoder2;
import per.study.netty.handler.codec.MyLongToByteEncoder;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/7
 **/
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
//                .addLast(new MyByteToLongDecoder())
                .addLast(new MyByteToLongDecoder2())
                .addLast(new MyLongToByteEncoder())
                .addLast(new MyServerHandler())
        ;
    }
}
