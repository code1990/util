package ocr;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class BaiduOCR {
    //设置APPID/AK/SK
    public static final String APP_ID = "16393850";
    public static final String API_KEY = "grU7EKi9Zfp1K4GjPxh9MdOb";
    public static final String SECRET_KEY = "fW7ZkXsKG6gVPdGoBWxz2QlmArSHFAZF";

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        String tmp = BaiduOCR.class.getClassLoader().getResource("log4j.properties").getPath();
        System.setProperty("aip.log4j.conf", tmp);
        // 调用接口
        String path = "E:\\github\\test\\null0.png";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
//        System.out.println(res.toString(2));
        JSONArray jsonArray = res.getJSONArray("words_result");
//        System.out.println(jsonArray.length());
        StringBuilder sb = new StringBuilder();
        for(Object obj:jsonArray){
            String content = ((JSONObject)obj).get("words").toString();
            sb.append(content+"\n");
        }
        System.out.println(sb.toString());

    }

    public static String ocrImage(String fileName){
        String result = "";
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 调用接口
        JSONObject res = client.basicGeneral(fileName, new HashMap<String, String>());
        JSONArray jsonArray = res.getJSONArray("words_result");
        StringBuilder sb = new StringBuilder();
        for(Object obj:jsonArray){
            String content = ((JSONObject)obj).get("words").toString();
            sb.append(content+"\n");
        }
        result = sb.toString();
        return result;
    }
}
