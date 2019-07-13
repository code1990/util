import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @author issuser
 * @date 2019-06-26 15:25
 */
public class RegexUtil {

    /*匹配所有的数字类型*/
    private static final String ALL_NUMBER="^[0-9]*$";
    /*匹配N位小数*/
    private static final String N_NUMBER="^\\d{n}$";
    /*至少n位的数字：^*/
    private static final String N_MORE_NUMBER="^\\d{n,}$";
    /*m-n位的数字*/
    private static final String M_N_NUMBER="^\\d{m,n}$";

//    private static final String REGEX_NUMBER="";
//    private static final String REGEX_NUMBER="";
//    private static final String REGEX_NUMBER="";
//    private static final String REGEX_NUMBER="";
//    private static final String REGEX_NUMBER="";

    public static boolean isNumber(String str){
        boolean result = false;
        result = Pattern.matches("^\\d{1,3}$", str);
        return result;
    }

    public boolean isNumber(String str,int index){
        boolean result = false;
        result = Pattern.matches("^\\d{1,3}$", str);
        return result;

    }

    public boolean isNumber(String str,int begin,int end){
        boolean result = false;
        result = Pattern.matches("^\\d{1,3}$", str);
        return result;

    }
    
    @Test
    public void testInfo(){
        for (int i = 1001; i <=2968 ; i++) {
            System.out.println(i);
        }
    }
}
