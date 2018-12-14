package rpc.javarmi;

import jdk.internal.org.objectweb.asm.commons.SerialVersionUIDAdder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 服务端必须继承UnicastRemoteObject类 该类定义了服务调用方与服务提供方对象实例 并且建立一对一的连接
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    public HelloServiceImpl() throws RemoteException {

    }

    @Override
    public String sayHello(String someOne) throws RemoteException {
        return null;
    }
}
