import org.json.JSONObject;
import translate.TransApi;

public class BaiduTranslate {
    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20181026000225582";
    private static final String SECURITY_KEY = "jxlXSB9CMR8KqFLm8CFQ";

    public static void main(String[] args) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "account for";
        System.out.println(api.getTransResult(query, "en", "zh"));
        String str = api.getTransResult(query, "en", "zh");
        JSONObject obj = new JSONObject(str);

        System.out.println(obj.get("trans_result"));
    }


}
