package rpc.javarmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RPC远程过程调用 用来实现部署在不同机器之间的系统之间的方法调用，是的程序可以像访问本地系统
 * 资源一样 通过网络传输去访问远端资源
 *
 *Java RMI 是一种基于Java语言的远程方法调用 可以让部署在不同主机上的java对象可以透明的通信
 *
 * 特点：支持面向对象的多态性 Java语言  使用了java的序列化机制  底层通信基于BIO
 * 基于BIO 导致性能不佳
 */
public interface HelloService extends Remote{
    /*RMI接口方法必须显示声明抛出RemoteException*/
    String sayHello(String someOne) throws RemoteException;
}
