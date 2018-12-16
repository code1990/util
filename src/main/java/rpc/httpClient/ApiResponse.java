package rpc.httpClient;


import java.io.Serializable;

/*
* 1.6	HTTPClient介绍

超文本传输协议（HTTP)可能是现在互联网上使用的最重要的协议，
WebServicesSOAP结构数据就是使用HTTP协议进行传输的一种数据协议。

新的HttpComponents分为HttpClient与HttpCore两个模块。
HttpClient提供了可以直接使用的面向用户方法，
HttpCore提供了较低层次的HttpAPI，可以用来定制个性化的客户端与服务端HTTP服务。
HttpClient的使用流程如下所述。

(1)构建HttpClient对象。

//默认值方式构建HttpClient对象
CloseableHttpClient httpclient = HttpClients.createDefault();


／设置常用的自定义参数初始化HttpClient对象
CloseableHttpClient httpClient = HttpClients.custom()
//自定义连接管理策略
.setConnectionManager(...)
//设置最大连接数
.setMaxConnTotal(...)
//自定义重试策略
.setRetryHandler(...)
//自定义拦截器
.addinterceptorF工rst(...)
//自定义连接过期策略
.setKeepAliveStrategy(...).build();


(2)构建URI对象。

／／构建请求的URL,http://localhost:8080/hello/sayHello.json?userName=liyebingURIuri=newURIBuilder()
URI uri = new URIBuilder()
.setScheme("http")
.setHost("localhost")
.setPort(8080)
.setPath("／hello/sayHello.json")
.setParameter("userName”，”liyebing")
.build();

(3)根据请求类型（GET、POST等）创建相应的请求对象CHttpGet、HttpPost等），并设置请求参数。

HttpClient支持HTTP/I.I规范中定义的Get、Head、Post、Put、Delete、Trace和Options。
HttpClient为其提供了特定的对应的对象HttpGet、HttpHead、HttpPost、HttpPut、HttpDelete、
HttpTrace及HttpOptions。

／／构建HttpGet对象
StringuriGet＝”http:I/localhost:8080/hello/sayHello.json?userName=liyebing”；
HttpGethttpGet=newHttpGet(uriGet);


／／构建HttpPost对象
StringuriPost＝”http://localhost:8080/hello/sayHello.json";
HttpPosthttpPost=newHttpPost(uriPost);
List<NameValuePair>list=newArrayList<NameValuePair>();
list.add(newBasicNameValuePair("userName","liyebing"));
httpPost.setEntity(newUrlEncodedFormEntity(list));



(4)调用HttpClient对象的execute方法发起调用。

(5)从返回结果中获取调用结果。

下面介绍如何使用HttpClient。


* 案例的开发流程
*
*1.定义HttpClientInvoke
*2.定义HelloController来处理请求
*3.定义统一的API接口处理json数据的返回与转发
*
* 使用web项目启动本案例
*
*
*
*
*
*
*
*
*
* @author: s1332177151@gmail.com 
* @Date: 2018/12/16 0016 16:40
*/


/*返回json结果 这里定义了通用的接口来返回json*/
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -2465140747749709626L;

    //请求是否成功 true/false
    private boolean success;
    //错误码
    private int code;
    //错误信息
    private String errMsg;
    //返回业务数据
    private T data;

    public static <T> ApiResponse<T> buildSuccess(T date) {
        ApiResponse<T> response = new ApiResponse<T>();
        response.setData(date);
        response.setSuccess(true);
        return response;
    }

    public static <T> ApiResponse<T> buildSuccess() {
        ApiResponse<T> response = new ApiResponse<T>();
        response.setSuccess(true);
        return response;
    }

    public static <T> ApiResponse<T> buildFailure() {
        ApiResponse<T> response = new ApiResponse<T>();
        response.setSuccess(false);
        return response;
    }

    public static <T> ApiResponse<T> buildFailure(String errorMsg) {
        ApiResponse<T> response = new ApiResponse<T>();
        response.setSuccess(false);
        response.setCode(0);
        response.setErrMsg(errorMsg);
        return response;
    }

    public static <T> ApiResponse<T> buildFailure(int errorCode, String errorMsg) {
        ApiResponse<T> response = new ApiResponse<T>();
        response.setSuccess(false);
        response.setCode(errorCode);
        response.setErrMsg(errorMsg);
        return response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}