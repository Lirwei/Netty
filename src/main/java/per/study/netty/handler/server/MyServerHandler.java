package per.study.netty.handler.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/7
 **/
public class MyServerHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("MyServerHandler receive data...");
        System.out.println("from client " + ctx.channel().remoteAddress() + " read long num: " + msg);
        // 给客户端发送Long数据
        System.out.println("send long num: 98765L");
        ctx.writeAndFlush(98765L);
        System.out.println("send over...");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
