package per.study.netty.rpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import per.study.netty.rpc.provider.HelloServiceImpl;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("is connected.");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 获取客户端发送的消息，并调用服务
        System.out.println("msg: " + msg);
        // 客户端在调用服务器的API时，需要定义一个协议
        // 要求：每次发送消息都必须以某个字符串开头 "HelloService#hello#"
        String header = "HelloService#hello#";
        if (msg.toString().startsWith(header)) {
            String result = new HelloServiceImpl().hello(msg.toString().substring(header.length()));
            ctx.writeAndFlush(result);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
