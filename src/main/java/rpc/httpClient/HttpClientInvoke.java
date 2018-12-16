package rpc.httpClient;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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


/*客户端调用请求*/
public class HttpClientInvoke {

    @Test
    public void test1() throws Exception {
        //构建HttpGet对象
        String uriGet = "http://localhost:8080/hello/sayHello.json?userName=liyebing";
        HttpGet httpGet = new HttpGet(uriGet);

        //构建HttpPost对象
        String uriPost = "http://localhost:8080/hello/sayHello.json";
        HttpPost httpPost = new HttpPost(uriPost);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("userName", "liyebing"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));

    }


    @Test
    public void testHttpGet() throws Exception {

        //构建请求的URL
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("localhost")
                .setPort(8080)
                .setPath("/hello/sayHello.json")
                .setParameter("userName", "liyebing")
                .build();

        //构建httpGet
        HttpGet httpGet = new HttpGet(uri);

        //请求http接口,并获取结果
        CloseableHttpClient httpclient = HttpClients.createDefault();
        ApiResponse response = httpclient.execute(httpGet, new ResponseHandler<ApiResponse>() {
            public ApiResponse handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                String result = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
                return JSON.parseObject(result, ApiResponse.class);
            }
        });
        //打印结果
        if (response.isSuccess()) {
            System.out.println(response.getData());
        }
    }

    @Test
    public void testFluentApi_1() throws IOException {

        String uri = "http://localhost:8080/hello/sayHello.json?userName=liyebing";
        String returnResult = Request.Get(uri)
                .execute().returnContent().asString(Charset.forName("utf-8"));

        ApiResponse response = JSON.parseObject(returnResult, ApiResponse.class);
        System.out.println(response.getData());
    }


    @Test
    public void testFluentApi_2() throws IOException {

        String uri = "http://localhost:8080/hello/sayHello.json?userName=liyebing";
        ApiResponse response = Request.Get(uri)
                .execute().handleResponse(new ResponseHandler<ApiResponse>() {
                    public ApiResponse handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                        String result = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
                        return JSON.parseObject(result, ApiResponse.class);
                    }
                });
        System.out.println(response.getData());
    }

    @Test
    public void testHttpContext() throws Exception {
        HttpContext context = new BasicHttpContext();
        HttpClientContext clientContext = HttpClientContext.adapt(context);

        String userName = "liyebing";
        URI uri = new URIBuilder().setScheme("http").setHost("localhost")
                .setPort(8080).setPath("/hello/sayHello.json").setParameter("userName", userName).build();

        //第一次请求
        CloseableHttpClient httpclient1 = HttpClients.createDefault();
        ApiResponse response = httpclient1.execute(new HttpGet(uri), new ResponseHandler<ApiResponse>() {
            public ApiResponse handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                String result = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
                return JSON.parseObject(result, ApiResponse.class);
            }
        }, clientContext);
        System.out.println(response.getData());


        //第二次请求
        CloseableHttpClient httpclient2 = HttpClients.createDefault();
        response = httpclient2.execute(new HttpGet(uri), new ResponseHandler<ApiResponse>() {
            public ApiResponse handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                String result = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
                return JSON.parseObject(result, ApiResponse.class);
            }
        }, clientContext);
        System.out.println(response.getData());
    }


    @Test
    public void multiThreadHttpExecution() throws Exception {
        //连接管理,设置最大连接数
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(10);

        //获取httpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        //创建请求的URL列表
        List<String> urisToGet = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            urisToGet.add("http://localhost:8080/hello/sayHello.json?userName=liyebing_" + i);
        }


        //多线程并发请求httpClient
        int uriSize = urisToGet.size();
        CountDownLatch countDownLatch = new CountDownLatch(uriSize);
        CompletionService<ApiResponse> completionService = new ExecutorCompletionService<ApiResponse>(Executors.newFixedThreadPool(10));
        for (String uri : urisToGet) {
            completionService.submit(new HttpInvokeCallable(httpClient, new HttpGet(uri), countDownLatch));
        }
        countDownLatch.await();

        //获取多线程请求的结果
        for (int i = 0; i < uriSize; i++) {
            ApiResponse response = completionService.take().get();
            System.out.println(response.getData());
        }
    }


    @Test
    public void testFutureRequest() throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().setMaxConnPerRoute(2).build();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        FutureRequestExecutionService futureRequestExecutionService = new FutureRequestExecutionService(httpClient, executorService);

        String url = "http://localhost:8080/hello/sayHello.json?userName=liyebing";
        HttpRequestFutureTask<ApiResponse> task = futureRequestExecutionService.execute(new HttpGet(url), HttpClientContext.create(), new ResponseHandler<ApiResponse>() {
            public ApiResponse handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                String result = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
                return JSON.parseObject(result, ApiResponse.class);
            }
        });

        ApiResponse response = task.get();
        System.out.println(response.getData());

    }


    @Test
    public void testAsynHttpClient() throws Exception {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            httpclient.start();
            String url = "http://localhost:8080/hello/sayHello.json?userName=liyebing";
            HttpGet request = new HttpGet(url);
            Future<HttpResponse> future = httpclient.execute(request, null);
            HttpResponse response = future.get();
            String result = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
            ApiResponse apiResponse = JSON.parseObject(result, ApiResponse.class);

            if (apiResponse.isSuccess()) {
                System.out.println(apiResponse.getData());
            }
        } finally {
            httpclient.close();
        }
    }
}
