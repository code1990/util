package rpc.webservice_cxf;

import javax.jws.WebService;

/*
*WebService是一种跨平台的RPC技术协议
* WebService技术栈是有SOAP 简单对象访问协议 UDDI统一描述发现集成 WSDL网络服务描述语言
* SOAP 是一种使用XML通信进行通信编码的协议 独立于平台 简单可拓展 基于HTTP可以绕过防火墙
* UDDI 独立于平台的框架 通用的描述发现与集成服务
* WSDL 使用XML编写的网络服务描述语言 描述webservice以及如何访问webservice
*
* Apache CXF 支持多种传输协议和协议绑定，数据绑定
*
* 本案例说明：
* 1.定义HelloService接口
* 2.定义HelloServiceImple实现类
* 3.cxf-server.xml配置文件
* 4.web.xml指定
* 5.cfx-client.xml配置文件
* 6.CxfClient编写说明
*
* 运行说明：配置为web项目 添加 Tomcat 浏览器访问 http://localhost:8080/ws/HelloWorld
* @Author: s1332177151@gmail.com 
* @Date: 2018/12/16 0016 9:28
*/
/*定义服务端接口 HelloService 使用@WebService注解标明是一个webservice远程服务接口*/
@WebService
public interface HelloService {

    public String sayHello(String content);

}
