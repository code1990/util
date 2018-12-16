package rpc.thrift_simple;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;

/*@ThriftService定义服务接口*/
@ThriftService("HelloService")
public interface HelloService {

    /*方法定义*/
    @ThriftMethod
    /*下面的@ThriftField包含参数定义*/
    public String sayHello(@ThriftField(name = "user") User user);


}
