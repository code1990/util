package rpc.diy_rpc;

/*
*
*RPC实现了client和server之间点对点的调用流程

而分布式服务框架,除了包括RPC的特性,还包括负载均衡策略及实现,服务的注册,发布与引入,以及服务的高可用策略,服务治理等特性。

* 自定义RPC框架的实现 使用javasocket来实现
*
* 1.定义一个对外提供服务的接口规范 Service API
* 2.定义了接口代理类 与服务的提供方通信 CosumerProxy
* 3.定义一个服务的提供方 通过java反射技术实现服务的调用
* 4.远程服务的实现类
*
*具体的案例如下
* 1.定义一个服务接口HelloService
* 2.定义一个远程服务接口实现类 HelloServiceImpl
* 3.服务消费代理类 ConsumerProxy
* 4.服务提供方  ProviderReflect
* 5.发布服务 RPCProviderMain
* 6.服务调用方 发起调用RpcConsumerMain
*
* 运行方式 启动 RPCProviderMain 再启动 RpcConsumerMain
*
*
* @author: s1332177151@gmail.com
* @Date: 2018/12/16 0016 17:02
*/

/*服务的启动*/
public class RpcProviderMain {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        ProviderReflect.provider(service, 8083);
    }

}
