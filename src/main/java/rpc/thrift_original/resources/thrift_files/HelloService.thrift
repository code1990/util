namespace java rpc.thrift_original.server

include 'UserModel.thrift'

service HelloService{
  string sayHello(1:UserModel.User user);
}