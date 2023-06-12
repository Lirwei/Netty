package per.study.netty.rpc.consumer;

import per.study.netty.rpc.netty.NettyClient;
import per.study.netty.rpc.publicinterface.HelloService;

public class ClientBootstrap {
    public static final String PLACEHOLDER = "HelloService#hello#";

    public static void main(String[] args) throws InterruptedException {

        NettyClient consumer = new NettyClient();
        HelloService helloService = (HelloService) consumer.getBean(HelloService.class, PLACEHOLDER);
        Thread.sleep(2000L);
        String result = helloService.hello("hello rpc ~");
        System.out.println("consumer receive msg [" + result + "].");
    }
}
