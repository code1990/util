package net;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 200 - 请求成功
 301 - 资源（网页等）被永久转移到其它URL
 404 - 请求的资源（网页等）不存在
 500 - 内部服务器错误

 */
public class HttpClientUtil {

    /**
     * 创建一个默认的客户端来处理
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        // 创建默认的客户端实例
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建get请求实例
        HttpGet httpget = new HttpGet("http://www.baidu.com");

        System.out.println("executing request " + httpget.getURI());

        try {
            // 客户端执行get请求返回响应
            CloseableHttpResponse response = httpClient.execute(httpget);

            // 服务器响应状态行
            System.out.println(response.getStatusLine().toString());

            Header[] heads = response.getAllHeaders();
            System.out.println(response.getHeaders("Content-Type"));
            // 打印所有响应头
            for (Header h : heads) {
                System.out.println(h.getName() + ":" + h.getValue());
            }
        } finally {
            httpClient.close();
        }
    }

    /**
     * 打印响应信息
     */
    @Test
    public void testReponse() throws Exception {
        //创建客户端
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //创建请求Get实例
        HttpGet httpGet = new HttpGet("https://www.baidu.com");

        //添加头部信息模拟浏览器访问
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml," +
                "application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch, br");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36" +
                " (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

//        try {
        //客户端执行httpGet方法，返回响应
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);

        //得到服务响应状态码
        if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
            System.out.println("====================");
//            HttpEntity entity = closeableHttpResponse.getEntity();
//            System.out.println(entity.getContent());
            //得到响应实体
            String entity = EntityUtils.toString(closeableHttpResponse.getEntity(), "utf-8");
            System.out.println(entity);
        } else {
            //如果是其他状态码则做其他处理
            System.out.println(closeableHttpResponse.getStatusLine().getStatusCode());
        }
//    } catch(ClientProtocolException e1) {
//        e1.printStackTrace();
//    } catch(IOException e2){
//        e2.printStackTrace();
//    }
    }

    /**
     * 添加cookie模拟用户登录
     */
    @Test
    public void testLogin() throws Exception{
        //创建客户端
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //创建请求Get实例
        HttpGet httpGet = new HttpGet("https://www.baidu.com");

        //设置头部信息进行模拟登录（添加登录后的Cookie）
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml," +
                "application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch, br");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        //httpGet.setHeader("Cookie", ".......");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36" +
                " (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

        try {
            //客户端执行httpGet方法，返回响应
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);

            //得到服务响应状态码
            if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
                //打印所有响应头
                Header[] headers = closeableHttpResponse.getAllHeaders();
                for (Header header : headers) {
                    System.out.println(header.getName() + ": " + header.getValue());
                }
            }
            else {
                //如果是其他状态码则做其他处理
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeableHttpClient.close();
        }
    }

    /**
     * 模拟登录人人网
     *
     * 模拟登录百度账户如下 所示
     * http://baogege.info/2016/04/21/baidu-login-with-httpclient/
     */
    @Test
    public void testPostData(){

        //创建默认客户端
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //创建Post请求实例
        HttpPost httpPost = new HttpPost("http://www.renren.com/");

        //创建参数列表
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("domain", "renren.com"));
        list.add(new BasicNameValuePair("isplogin", "true"));
        list.add(new BasicNameValuePair("submit", "登录"));
        list.add(new BasicNameValuePair("email", ""));
        list.add(new BasicNameValuePair("passwd", ""));

        //向对方服务器发送Post请求
        try {
            //将参数进行封装，提交到服务器端
            httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF8"));
            CloseableHttpResponse httpResponse = closeableHttpClient.execute(httpPost);

            //如果模拟登录成功
            if(httpResponse.getStatusLine().getStatusCode() == 200) {
                Header[] headers = httpResponse.getAllHeaders();
                for (Header header : headers) {
                    System.out.println(header.getName() + ": " + header.getValue());
                }
            }else {
                System.out.println(httpResponse.getStatusLine().getStatusCode());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpPost.abort();      //释放资源
        }
    }

    //如果想获取源代码 直接使用Jsoup
    @Test
    public void testJsoup() throws Exception{
        int status = Jsoup.connect("http://www.xicidaili.com/nt/381").proxy("112.95.190.109",9797).response().statusCode();
        System.out.println(status);
        Document document = Jsoup.connect("http://www.xicidaili.com/nt/381").timeout(10000).get();
        System.out.println(document.toString());
//        Html
       //直接解析一个html页面
//        Document document = Jsoup.parse(html);
//给定页面直接解析
        File file = new File("/tmp/example.html");
        Jsoup.parse(file, "utf8", "http://... ...");


    }

    @Test
    public void testJsoupSelect(){
        Document document = Jsoup.parse("");
        //标签名
        Elements elements = document.select("span");
        //id
        elements = document.select("#mySpan");
        //class
        elements = document.select(".myClass");
        //标签内联属性 一个元素的多个属性加[] <span class="class2" id="id2">36</span>
        elements = document.select("span[class=class1]span[id=id1]");
        //根据内联属性前缀
        elements = document.select("span[^cl]");
        //内联属性名+正则表达式
        elements = document.select("span[class=~AB]");
        //文本标签内容
        elements = document.select("span:contains(3)");
        //签文本包含某些内容+正则表达式来查找：
        elements = document.select("span:matchesOwn(^3)");//3开头



    }
}
