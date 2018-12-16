package rpc.thrift_simple;

import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.swift.service.ThriftClientManager;

import java.net.InetSocketAddress;

/*
* 使用注解来简化Thrift服务的实现
*
* 代码生成器生成的HelloService User类的可读性很差 不利于拓展 使用注解开发
*
*主要的java注解
@ThriftService	    标注Java类为Thrift服务
@ThriftMethod	    与＠ThriftService结合使用,标注Java类中的方法为Thrift服务中的方法
@ThriftStruct	    标注Thrift中的struct
@ThriftConstructor	与＠ThriftStruct结合使用，标注Thrift中的struct的构造方法
@ThriftField	    与＠ThriftStruct结合使用标注Thrift中struct的属性
                    或者与＠ThriftService及＠ThritMethod结合使用，标注Thrift服务中方法参数
*
*基于注解的开发版实例
*1.定义User 数据结构
*2.定义HelloService 定义服务接口
*3.定义HelloService 服务接口实现类
*4.定义ServerMain 服务端启动类
*5.定义ClientMain 客户端调用代码
*
* 代码运行过程：先启动ServerMain 在启动ClientMain
*
* @author: s1332177151@gmail.com
* @Date: 2018/12/16 0016 14:34
*/


/*thrift服务端调用代码*/
public class ClientMain {

    public static void main(String[] args)throws Exception {
        ThriftClientManager clientManager = new ThriftClientManager();
        FramedClientConnector connector = new FramedClientConnector(new InetSocketAddress("localhost",8899));

        User user = new User();
        user.setName("liyebing");
        user.setEmail("test@163.com");

        HelloService helloService = clientManager.createClient(connector,HelloService.class).get();
        String hi= helloService.sayHello(user);
        System.out.println(hi);
    }

}
