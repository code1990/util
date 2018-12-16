package rpc.webservice_axis2;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;

import javax.xml.namespace.QName;

/**
 * WebService是一种跨平台的RPC技术协议
 * WebService技术栈是有SOAP 简单对象访问协议 UDDI统一描述发现集成 WSDL网络服务描述语言
 * SOAP 是一种使用XML通信进行通信编码的协议 独立于平台 简单可拓展 基于HTTP可以绕过防火墙
 * UDDI 独立于平台的框架 通用的描述发现与集成服务
 * WSDL 使用XML编写的网络服务描述语言 描述webservice以及如何访问webservice
 *
 * Apache Axis2是新一代的SOAP引擎 存在java+c2种实现
 * 特点：高性能 热部署 异步服务支持 wsdl支持
 *
 * 本案例说明：
 * 1.定义HelloService接口
 * 2.定义HelloServiceImpl实现
 * 3.axis2-server.xml 声明spring bean
 * 4.services.xml 来描述Axis2服务
 * 5.web.xml配置
 * 6.Axis2Client
 *
 *
 * 运行说明：配置为web项目 添加 Tomcat 浏览器访问 http://localhost:8080/ws/HelloWorld
 *
 * @author: s1332177151@gmail.com
 * @Date: 2018/12/16 0016 11:01
 */


/*客户端调用的方式有多种 不用预生成代码的动态调用方式*/
public class Axis2Client {


    public static void main(String[] args) {
        try {
            // http://localhost:8080/services/HelloService?wsdl
            EndpointReference targetEPR = new EndpointReference(
                    "http://localhost:8080/services/HelloService");
            RPCServiceClient serviceClient = new RPCServiceClient();
            Options options = serviceClient.getOptions();
            options.setManageSession(true);
            options.setProperty(HTTPConstants.REUSE_HTTP_CLIENT, true);
            options.setTo(targetEPR);
            QName opQName = new QName("http://service.axis2", "sayHello");
            Object[] paramArgs = new Object[]{"kongxuan"};

            //处理返回数据
            Class[] returnTypes = new Class[]{String.class};
            Object[] response = serviceClient.invokeBlocking(opQName, paramArgs, returnTypes);
            serviceClient.cleanupTransport();
            String result = (String) response[0];
            if (result == null) {
                System.out.println("HelloService didn't initialize!");
            } else {
                System.out.println(result);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
