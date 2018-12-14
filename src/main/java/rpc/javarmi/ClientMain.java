package rpc.javarmi;

import java.rmi.Naming;
/*客户端远程服务调用代码*/
/*先启动ServerMain 再启动ClientMain*/
public class ClientMain {

    public static void main(String[] args) throws Exception {
        //服务的引入
        HelloService helloService = (HelloService) Naming.lookup("rmi://localhost:8801/helloService");
        //调用远程方法
        System.out.println("RMI服务器返回的结果是"+helloService.sayHello("jim"));
    }
}
