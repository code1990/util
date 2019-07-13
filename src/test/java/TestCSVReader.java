import io.CsvReader;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author issuser
 * @date 2019-06-26 14:33
 */
public class TestCSVReader {

    @Test
    public void testInfo() throws Exception {
        List<String[]> result = null;
        result = CsvReader.read(new FileInputStream(new File("C:\\Users\\issuser\\Desktop\\201901.txt")));
        StringBuilder stringBuilder = new StringBuilder();
        for (String[] ss : result) {
            if (!stringBuilder.toString().contains(ss.length + "")) {
                stringBuilder.append(ss.length + "\n");
            }
        }
        System.out.println(stringBuilder.toString());
    }

    @Test
    public void testAdd() {
        System.out.println(new BigDecimal(206888542.84).add(new BigDecimal(282712711.81)));
        System.out.println(new BigDecimal(1012995.82).add(new BigDecimal(1765808.92)));
    }

    @Test
    public void testInfo2() {
        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\CET4.txt");
        for (String s : list) {
            if (!s.contains("英语四级") && !Pattern.matches("^\\d{1,3}$", s) && s.trim().length() != 1) {
//                if(s.split("\t").length==1){
//                    System.out.println(s.split("\t")[0].trim());
//                }else{
//                    System.out.println(s.split("\t")[1].trim());
//                }
                System.out.println(s.split("/")[0] + "\t" + s.split("/")[s.split("/").length - 1]);
            } else {
//                System.out.println("===="+s);
            }

        }
    }
    @Test
    public void testInfo123() throws Exception {
        List<String[]> result = null;
        result = CsvReader.read(new FileInputStream(new File("C:\\Users\\issuser\\Desktop\\123.csv")));
        StringBuilder stringBuilder = new StringBuilder();
        for (String[] ss : result) {
            System.out.println(Arrays.toString(ss));
        }
        System.out.println(stringBuilder.toString());
    }
}