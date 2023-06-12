package per.study.netty.rpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {
    private ChannelHandlerContext context; // 上下文
    private String result; // 服务方返回结果
    private String para; // 客户端调用方法时，传入的参数

    // 第一个被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive is called.");
        context = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead is called.");
        result = msg.toString();
        notify();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    /**
     * 被代理对象调用，发送数据给服务器 -> wait -> 等待被唤醒 -> 返回结果
     */
    @Override
    public synchronized Object call() throws Exception {
        System.out.println("call-1 is called.");
        System.out.println(this.para);
        System.out.println(context.writeAndFlush(this.para));
        wait();
        System.out.println("call-2 is called.");
        return result;
    }

    public void setPara(String para) {
        System.out.println("setPara is called.");
        this.para = para;
    }
}
