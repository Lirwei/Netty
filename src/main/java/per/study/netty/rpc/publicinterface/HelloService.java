package per.study.netty.rpc.publicinterface;

/**
 * 服务提供方和服务消费方都需要的
 */
public interface HelloService {
    String hello(String mes);
}
