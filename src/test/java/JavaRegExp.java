import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaRegExp {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher("ABC");
        if( !isNum.matches() ){
            System.out.println(false);
        }else{
            System.out.println(true);
        }

    }
}
