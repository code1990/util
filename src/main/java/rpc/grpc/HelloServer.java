package rpc.grpc;

//import com.grpc.example.HelloRequest;
//import com.grpc.example.HelloResponse;
//import com.grpc.example.HelloServiceGrpc;
/*
* grpc 一个高性能 、开源和通用的 RPC 框架 ，面向移动和 HTTP/2 设计
*
* 1.5.1	protobuf3语法介绍

protobuf3的IDL主要由message(消息),enum(枚举),map(映射),service(服务)等数据结构或者元素构成.

(1)message消息数据结构.

先看message的一个简单例子：
syntax＝”proto3”;//表示使用proto3语法 没有则使用proto2
message Request{
string name=l;
int32 limit=2;
}

message结构由系列字段属性构成,字段类型可以是简单类型,
也可以是message或者其他复合类型.

简单类型与Java语言的对应关系如表1-2所示.

.proto类型	Java类型	说明
double	double
float	float
int32	mt	使用变长编码,对于负值的效率很低,如果域可能有负值,请使用sint64替代
umt32	mt	使用变长编码
uint64	long	使用变长编码
sint32	mt	使用变长编码,这些编码在负值比int32而效得多
sint64	long	使用变长编码,有符号的整型值,编码时比通常的int64高效
fixed32	mt	总是4个字节,如果数值总是比228大,这个类型会比uint32高效
fixed64	long	总是8个字节,如果数值总是比256大,这个类型会比uint64高效
sfixed32	mt	总是4个字节
sfixed64	long	总是8个字节
boolean		boolean
string	String	一个字符串必须是UTF-8编码或者7-bitASC门编码的文本
bytes	ByteString	可能包含任意顺序的字节数据

完整的属性声明组成：［字段规则］字段类型变量名称＝标识号.

其中［字段规则］是可选的,有singular与repeated规则.
singular,一个格式良好的消息应该有0个或者l个这种宇段(但是不能超过i个);
repeated,在一个格式良好的消息中,这种字段可以重复任意多次(包括0次),重复的值的顺序会被保留.

标识号是用来在消息的二进制格式中识别各个字段的,一旦开始使用就不能够再改变.

如果一个已有的消息格式已无法满足新的需求,需要在消息中添加一个额外的字段,

(2)enum枚举类型.

每个枚举类型必须将其第一个类型映射为0,这个零值必须为第一个元素,为了兼容
proto2语义,枚举类的第一个值总是默认值.例如：

enum STATUS{

  UNKNOWN=0;
  KNOWN=1;

}


(3)map数据类型.

map代表一种关联映射关系.例如：

map<string,int32> nameidMap = 2;

当为.proto文件生成文本格式的时候,map会按照key的顺序排序,数值化的key会按照数值排序.

(4)Service服务定义.

在.proto文件中定义一个RPC服务接口,
ProtocolBuffer编译器会根据所选择的不同语言生成服务接口代码及存根.例如：


service HelloService{
//接收Request,返回一个Response.
rpc	sayHello(Request)returns(Response);
}

*
*
*
* grpc案例如下：
*
* 1.添加 Maven 插件 protobuf-maven-plugin 可以编译.proto文件
* mvn protobuf:compile 用来编译.proto文件中的message数据结构 生成对应的java类
 mvn protobuf:compile-custom 用来编译.proto文件中service定义 生成对应的servie基类
*
*2.编写sayHello.proto文件
*
* 3.分别使用mvn protobuf:compile mvn protobuf:compile-custom 来编译生产文件
*
* 4.编写服务发布类 HelloServer
*
* 5.编写客户端调用类 HelloClient
*
* 运行过程：运行HelloServer的Main方法对外提供grpc服务
*         再启动HelloClient的main方法 启动服务调用
*
* 本地编译不通过 代码全部注释 见mvn_protobuf.png 只做案例说明 遇到实战 在分析处理
*
*
*
* @author: s1332177151@gmail.com
* @Date: 2018/12/16 0016 15:21
*/

/*定义服务发布类*/
/*
public class HelloServer {

    //服务端口
    private int port = 50051;
    //服务端服务管理器
    private Server server;


    private void start() throws IOException {
        //初始化并启动服务
        server = ServerBuilder.forPort(port)
                .addService(new HelloServiceImpl())
                .build()
                .start();

        //注册钩子,JVM退出的时候停止服务
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                HelloServer.this.stop();
            }
        });
    }

    //停止服务
    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    // 阻塞一直到退出程序
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }


    // 实现 定义一个实现服务接口的类
    private class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

        public void sayHello(HelloRequest req, StreamObserver<HelloResponse> responseObserver) {
            //构建返回结果对象
            HelloResponse reply = HelloResponse.newBuilder().setMessage(("hello," + req.getName())).build();
            //将返回结果传入stream,返回给调用方
            responseObserver.onNext(reply);
            //通知stream结束
            responseObserver.onCompleted();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        final HelloServer server = new HelloServer();
        server.start();
        server.blockUntilShutdown();
    }

}
*/
