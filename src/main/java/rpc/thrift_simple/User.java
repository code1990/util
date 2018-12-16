package rpc.thrift_simple;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftStruct;

/*定义数据结构 @ThriftStruct 对应struct类型  @ThriftField 对应标注属性 属性定义*/
@ThriftStruct
public final class User {

    private String name;

    private String email;

    @ThriftField(1)
    public String getName() {
        return name;
    }

    @ThriftField
    public void setName(String name) {
        this.name = name;
    }

    @ThriftField(2)
    public String getEmail() {
        return email;
    }

    @ThriftField
    public void setEmail(String email) {
        this.email = email;
    }
}
