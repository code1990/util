package rpc.javarmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 第一章 常用的RPC框架
 *
 * RPC远程过程调用 用来实现部署在不同机器之间的系统之间的方法调用，是的程序可以像访问本地系统
 * 资源一样 通过网络传输去访问远端资源
 *
 * Java RMI 是一种基于Java语言的远程方法调用 可以让部署在不同主机上的java对象可以透明的通信
 *
 * 特点：支持面向对象的多态性 Java语言  使用了java的序列化机制  底层通信基于BIO
 * 基于BIO 导致性能不佳
 *
 * 本案例说明：
 * 1.定义HelloService接口
 * 2.定义HelloServiceImpl实现类
 * 3.定义ServerMain服务端方法
 * 4.定义ClientMain方法
 * 5.定义CustomerSocketFactory来穿透防火墙
 *
 * 6.运行方法是
 * 先启动ServerMain
 * 再启动ClientMain
 *
 * @author issuser
 */
public interface HelloService extends Remote{
    /*RMI接口方法必须显示声明抛出RemoteException*/
    String sayHello(String someOne) throws RemoteException;
}
