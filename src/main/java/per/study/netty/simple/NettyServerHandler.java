package per.study.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;


/**
 * 自定义一个 Handler 需要继承 Netty 规定好的某个 HandlerAdapter
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据
     *
     * @param ctx 上下文对象，含有管道pipeline，通道，地址
     * @param msg 客户端发送的数据，默认Object
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    System.out.println("hello, client...1");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20 * 1000);
                    System.out.println("hello, client...2");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20 * 1000);
                    System.out.println("hello, client...2");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 5, TimeUnit.SECONDS);

        System.out.println("Server process thread: " + Thread.currentThread().getName());
        System.out.println("Server ctx = " + ctx);
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("msg send from  client: " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("client remote address: " + ctx.channel().remoteAddress());
    }

    /**
     * 数据读取完毕
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // writeAndFlush: write flush
        // 将数据写入缓存，并刷新
        // 一般讲，需要对这个发送的信息进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, Client！", CharsetUtil.UTF_8));
    }

    /**
     * 处理异常，需要关闭通道
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
