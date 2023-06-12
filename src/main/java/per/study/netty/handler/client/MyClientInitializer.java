package per.study.netty.handler.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import per.study.netty.handler.codec.MyByteToLongDecoder2;
import per.study.netty.handler.codec.MyLongToByteEncoder;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/7
 **/
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 加入一个出站的handler对数据进行一个编码
        pipeline.addLast(new MyLongToByteEncoder());
        // 入站解码器
//        pipeline.addLast(new MyByteToLongDecoder());
        pipeline.addLast(new MyByteToLongDecoder2());
        // 加入一个自定义的handler，处理业务
        pipeline.addLast(new MyClientHandler());
    }
}
