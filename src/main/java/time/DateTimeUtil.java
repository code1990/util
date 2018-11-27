package time;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author issuser
 * @date
 * @description 日期时间工具类
 */
public class DateTimeUtil {
    public static  String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmsssss");
        return sdf.format(new Date());
    }
    @Test
    public void test(){
        System.out.println(DateTimeUtil.getCurrentTime());
    }
}
