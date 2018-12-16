package rpc.diy_rpc;

import org.apache.commons.beanutils.MethodUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
 *
 * 服务发布实现对应ProviderReflect角色。
 * 通过Java对象输入流ObjectlnputStream从Socket中按照ConsumerProxy的写入顺序逐一获取调用方法名称及方法参数，
 * 通过org.apache.commons.lang3中的工具方法MethodUtils.invokeExactMethod对服务实现类发起反射调用，
 * 将调用结果写入Socket返回给调用方。
 */

public class ProviderReflect {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    /*服务的发布*/
    public static void provider(final Object service,int port) throws Exception{
        if(service == null || port <0 || port > 65535){
            throw new IllegalArgumentException("Illegal param");
        }
        ServerSocket serverSocket = new ServerSocket(port);
        while (true){
            final Socket socket = serverSocket.accept();
            EXECUTOR_SERVICE.execute(new Runnable() {
                @Override
                public void run() {
                    ObjectInputStream objectInputStream = null;
                    ObjectOutputStream objectOutputStream = null;
                    try {
                        objectInputStream = new ObjectInputStream(socket.getInputStream());
                        String methodName = objectInputStream.readUTF();
                        Object[] args = (Object[])objectInputStream.readObject();
                        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        Object result = MethodUtils.invokeExactMethod(service,methodName,args);
                        objectOutputStream.writeObject(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            objectInputStream.close();
                            objectOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        }


    }







}
