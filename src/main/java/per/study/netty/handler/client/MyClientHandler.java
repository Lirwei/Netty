package per.study.netty.handler.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/7
 **/
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        // 接收服务器发来的数据
        System.out.println("Server IP: " + ctx.channel().remoteAddress());
        System.out.println("Msg receive from server: " + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler send data...");
        ctx.writeAndFlush(123456L);
    }
}
