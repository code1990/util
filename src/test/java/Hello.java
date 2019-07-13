import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Hello {
    public static void main(String[] args) {
        String tmpStr = "oas_error_log.2019-03-04.txt";
        String tmpStr2 = "oas_log.2019-04-01-0.txt";
        String FILE_NAME_REDEX = "^oas_error_log.\\d{4}-\\d{1,2}-\\d{1,2}.txt$";
        String FILE_NAME_REDEX2 = "^oas_log.\\d{4}-\\d{1,2}-\\d{1,2}-\\d{1,2}.txt$";
        System.out.println(Pattern.matches(FILE_NAME_REDEX, tmpStr));
        System.out.println(Pattern.matches(FILE_NAME_REDEX2, tmpStr2));
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Integer ii = 0;
        for(Integer s : list) {
            list.set(ii, s+2);
            ii++;
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(list.get(i));
        }
//        GoogleTranslateService("梦想");
        String fileName = "D:\\oas_log\\oas\\oas_error_log.2018-09-25";
        int index = fileName.lastIndexOf("\\");
        System.out.println(fileName.substring(0,index+1));
        System.out.println(fileName.substring(index+1,fileName.length()));
        System.out.println(fileName.substring(index+1,fileName.length()).split("\\.")[1]);
    }

    public static String  GoogleTranslateService(String str){
        String fromLanguage = "zh-CN";
        String toLanguage = "en";
        String url = "http://translate.google.cn/translate_a/single?client=t&sl={0}&tl={1}&hl={0}&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&dt=at&ie=UTF-8&oe=UTF-8&ssel=6&tsel=3&kc=0&tk=522626|172097&q={2}";

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
