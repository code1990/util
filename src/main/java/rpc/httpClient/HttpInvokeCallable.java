package rpc.httpClient;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

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


/*处理结果 回调函数 无引用*/
public class HttpInvokeCallable implements Callable<ApiResponse> {

    private final CloseableHttpClient httpClient;
    private final HttpGet httpGet;
    private final CountDownLatch countDownLatch;

    public HttpInvokeCallable(CloseableHttpClient httpClient, HttpGet httpGet, CountDownLatch countDownLatch) {
        this.httpClient = httpClient;
        this.httpGet = httpGet;
        this.countDownLatch = countDownLatch;
    }


    public ApiResponse call() throws Exception {
        try {
            return httpClient.execute(httpGet, new ResponseHandler<ApiResponse>() {
                public ApiResponse handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    String result = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
                    return JSON.parseObject(result, ApiResponse.class);
                }
            });
        } finally {
            countDownLatch.countDown();
        }
    }
}
