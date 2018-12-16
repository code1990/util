package rpc.thrift_simple;


/*服务接口的实现类 普通的javabean对象*/
public class HelloServiceImpl implements HelloService {
    public String sayHello(User user) {
        return "hello," + user.getName() + user.getEmail();
    }
}
