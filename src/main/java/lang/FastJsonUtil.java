package lang;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.junit.Test;

import java.util.List;

/**
 * SerializeWriter：相当于StringBuffer
 * JSONArray：相当于List<Object>
 * JSONObject：相当于Map<String, Object>
 * JSON：fastJson的解析器，用于JSON格式字符串与JSON对象及javaBean之间的转换
 *
 * @author issuser
 * <p>
 * public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
 * public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject
 * public static final <T> T parseObject(String text, Class<T> clazz); // 把JSON文本parse为JavaBean
 * public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
 * public static final <T> List<T> parseArray(String text, Class<T> clazz); //把JSON文本parse成JavaBean集合
 * public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
 * public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
 * public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。
 */
public class FastJsonUtil {

    @Test
    public void test() {
        //json 字符串
        String jsonStr = "{\"studentName\":\"lily\",\"studentAge\":12}";
        //json字符串转jsonObject
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        //json对象转字符串
        String jsonStr2 = JSONObject.toJSONString(jsonObject);

        //json字符串转数组
        String jsonStrArray = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";
        JSONArray jsonArray = JSONArray.parseArray(jsonStrArray);
        //数组的遍历
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {

            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            System.out.println("studentName:  " + jsonObject1.getString("studentName") + ":" + "  studentAge:  "
                    + jsonObject1.getInteger("studentAge"));
        }

        //json数组转字符串
        String jsonString = JSONArray.toJSONString(jsonArray);



    }
}
