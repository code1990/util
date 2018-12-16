package rpc.thrift_original;

import org.apache.thrift.async.AsyncMethodCallback;

import java.util.concurrent.CountDownLatch;

/*
* Apache Thrift是可伸缩的分布式RPC框架
*
* 多语言的支持 使用tcp/ip传输协议实现的一种RPC实现
*
* Thrift的工作流程：
* 1.设计所需要方服务
* 2.编写Thrift IDL服务描述文件
* 3.根据Thrift IDL服务描述文件使用 Thrift提供的代码生成工具生成客户端和服务端代码
* 4.实现业务逻辑的编写 实现客户端调用代码的编写
* 5.运行服务端和客户端
*
* Thrift内部同时包括 transport protocol processor server 四个部分
*
*
* transport 提供网络读写数据的方法
*    TSocket与TIOStreamTransport基于阻塞IO模型实现
*    TnonblockingTransport TNonblockingSocket 非阻塞IO实现
*    TMemoryInputTransport 封装了一个字节数组来做输入流的封装
*    TMemoryBuffer使用字节数组输出流ByteArrayOutputStream做输出流的封装。
*    TFramedTransport则封装了TMemoryInputTransport做输入流，封装
     TByteArryOutPutStream做输出流，作为内存读写缓冲区的一个封装。


* protocol  提供对网络传输数据进行序列化和反序列化的具体实现
*
* TbinaryProtocol:二进制格式传输协议。
  TCompactProtocol:压缩二进制格式传输协议。
  TJSONProtocol:JSON格式传输协议。
  TSimpleJSONProtocol:简单的JSON格式数据传输协议。
  TDebugProtocoI:调试时使用的文本传输协议。


* processor 通过使用编写的ThriftIDL描述文件来自动生成Processor。
* 它从负责输入的Protocol读取数据，将其传递给处理程序，并将结果发送到负责输出的Protocol
*
* server Server将Transport、Protocol、Processor组合在一起，将服务运行起来，在指定端口等待调用端的请求。
*
* TnonblockingServer:基于多线程非阻塞I/0模型实现，适用于连接数较多的高并发环境。
  TthreadPoolServer:基于多线程阻塞1/0模型实现，比TNonblockingServer需要耗费更多的系统资源。
  ThsHaServer:半同步、半异步服务器。
  TsimpleServer:基于单线程的阻塞1/0模型实现，主要用于测试，不推荐在生产环境中使用。


ThriftIDL语法说明

1.Thrift常用的数据类型

(l）基本类型。

boolean:布尔值true/false。
byte:Byte类型或者8位有符号整数。
i16:16位有符号整数。
i32:32位有符号整数。
i64:64位有符号整数。
double:64位双精度符号浮点数。
string:UTF-8格式编码的字符串。

(2）容器类型。

list<type＞:数组数据类型。
set<type＞:集合数据类型，不同于数组，集合内部的元素保持唯一。
map<typel,type2＞:对应于键值对（Key-Value）数据结构Map，其中Key保持唯一性。

(3）结构体类型。

struct:一组强类型对象的封装，类似于C语言中的struct类型。例如:

structUser{1:string name;2:i32age;}

(4)enum枚举类型。

和Java语言中的枚举类型含义一致。Thrift不支持枚举类嵌套，枚举常量必须是32
位的正整数。举例如下:

enum Status{ONE,TWO=2,THREE=3}

2 . 声明服务 类似于接口的写法
//namespace 指定java文件的包路径
namespace rpc.thrift_original.server
serviceHelloService{
string sayHello(l:UserModel.Useruser,2:stringcontent);
//调用端不会等待该 服务的响应
oneway void notifyMessage();
}

3.服务升级保持兼容性
(1)不要变更之前己经存在 的字段的编号值.
(2)新添加的字段可 以使用optional 进行修饰,(optional代表可选非必须属性)以便格式兼容.


 基于原生Thrift 的 Java版完整案例
 1.首先，编写ThriftlDL文件，这里一共有两个ThriftIDL文件，
      UserModel.thrift定义了数据结构，
      HelloService.thrift定义了服务的声明

 2.cmd命令 进入thrift-0.11.0.exe所在目录 执行如下命令
    thrift-0.11.0.exe -r -gen java UserModel.thrift
    thrift-0.11.0.exe -r -gen java HelloService.thrift
    会在 thrift-0.11.0.exe 所在的目录下生成如下文件
    Gen-java\rpc\thrift_original\HelloService.java
    Gen-java\rpc\thrift_original\User.java
    把生成的文件复制到工程目录中去

 3.编写服务端代码 HelloServiceImpl 实现业务逻辑

 4.编写服务启动方法与服务调用方法 完成服务的调用
    同步阻塞模式 SimpleInvoker
    异步阻塞模式 NonblockingInvoker AsynInvokerCallback为异步回调结果处理类

说明 ：ThriftSerializer 暂时没有使用 可以删除 是Thrift序列化的一种实现

 代码的运行方式 1.先运行startServer()方法启动服务端 在运行startClient发起客户端调用 完成一次服务的完整调用
*
* @author: s1332177151@gmail.com
* @Date: 2018/12/16 0016 13:09
*/

/*非阻塞I/O服务调用示例 结果处理类*/
public class AsynInvokerCallback implements AsyncMethodCallback<HelloService.AsyncClient.sayHello_call> {

    private CountDownLatch latch;

    public AsynInvokerCallback(CountDownLatch latch) {
        this.latch = latch;
    }


    /**
     * 异步调用完成,回调该方法
     *
     * @param response
     */
    public void onComplete(HelloService.AsyncClient.sayHello_call response) {
        try {
            System.out.println("AsynInvokerCallback response: " + response.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }

    /**
     * 异步调用出错回调方法
     *
     * @param exception
     */
    public void onError(Exception exception) {
        latch.countDown();
    }
}
