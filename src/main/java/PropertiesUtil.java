import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.Test;

/**
 * @author issuser
 * @date
 *
 */
public class PropertiesUtil {

    public void getInfo(String name){
        InputStream is = null;
        try {
            /*PropertiesUtil.class编译后与Config.properties处在同一个目录下*/
            is = PropertiesUtil.class.getResourceAsStream(name);
            Properties p = new Properties();
            p.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test(){
//        getInfo("Config.properties");
        System.out.println(PropertiesUtil.class.getResource("Config.properties").getPath());
    }


}
