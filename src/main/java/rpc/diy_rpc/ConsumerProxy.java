package rpc.diy_rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

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

/**
 * 服务消费者代理类实现对应ConsumerProxy角色。
 * 通过实现服务接口的动态代理对象获得服务接口的动态代理实例Proxy.newProxylnstance，
 * 通过实现InvocationHandler接口中的方法publicObjectinvoke(Objectproxy,Methodmethod,Object[]arguments）来完成远程RPC调用。
 * 具体通过Java对象输出流ObjectOutputStream将调用接口的方法及参数写入Socket，发起远程调用。
 * 之后通过Java对象输入流ObjectI叩utStream从Socket中获得返回结果。
 */

public class ConsumerProxy {

    /*服务消费代理接口*/
    public static <T> T consume(final Class<T> interfaceClass, final String host, final int port) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = null;
                        ObjectOutputStream output = null;
                        ObjectInputStream input = null;
                        Object result = null;
                        try {
                            socket = new Socket(host, port);
                            output = new ObjectOutputStream(socket.getOutputStream());
                            input = new ObjectInputStream(socket.getInputStream());
                            output.writeUTF(method.getName());
                            output.writeObject(args);
                            input.readObject();
                            if (result instanceof Throwable) {
                                throw (Throwable) result;
                            }

                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        } finally {
                            input.close();
                            output.close();
                            socket.close();

                        }
                        return result;
                    }
                });
    }

}
