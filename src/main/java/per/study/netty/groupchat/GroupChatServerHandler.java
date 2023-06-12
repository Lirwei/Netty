package per.study.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/6
 **/
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    // 定义一个channel组，管理所有的channel
    // GlobalEventExecutor.INSTANCE 是全局的事件执行器，是一个单例
    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Map<String, Channel> channels = new HashMap<>();

    /**
     * 表示连接建立，一旦连接，第一个被执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel currentChannel = ctx.channel();
        // 将客户加入聊天信息推送给其他在线的客户端
        channelGroup.writeAndFlush("[客户端]" + currentChannel.remoteAddress() + " 加入聊天 " + simpleDateFormat.format(new Date()) + "\n");
        channelGroup.add(currentChannel);

        channels.put("id100", currentChannel);
    }

    /**
     * 断开连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel currentChannel = ctx.channel();
//        channelGroup.remove(currentChannel);
        channelGroup.writeAndFlush("[客户端]" + currentChannel.remoteAddress() + " 离开聊天 " + simpleDateFormat.format(new Date()) + "\n");
        System.out.println("channelGroup size: " + channelGroup.size());
    }

    /**
     * channel处于活动状态，提示xx上线
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " " + simpleDateFormat.format(new Date()) + " 上线了");
    }

    /**
     * channel处于不活动状态，提示xx离线了
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " " + simpleDateFormat.format(new Date()) + " 离线了");
    }

    /**
     * 读取数据
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 获取到当前channel
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush("[客户]" + channel.remoteAddress() + "send msg: " + msg + simpleDateFormat.format(new Date()) + "\n");
            } else {
                ch.writeAndFlush("[you] send msg: " + msg + " " + simpleDateFormat.format(new Date()) + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 关闭通道
        ctx.close();
    }
}
