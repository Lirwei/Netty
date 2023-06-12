package per.study.netty.rpc.provider;

import per.study.netty.rpc.publicinterface.HelloService;

public class HelloServiceImpl implements HelloService {

    private static int count = 0;

    /**
     * 当消费方调用方法时，就返回一个结果
     *
     * @param mes
     * @return
     */
    @Override
    public String hello(String mes) {
        System.out.println("收到客户端消息：" + mes);

        if (null != mes) {
            return "hello client, receive msg [" + mes + "], count [" + (++count) + "]";
        }
        return "hello client, receive msg.";
    }
}
