import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class DoubanBookSearch {
    public static StringBuilder sb1 = new StringBuilder("*****************************************\n");
    private static final String key = "hbase";

    /*第一步爬取数据*/
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:\\sdk\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        int i = 0;
        while (i <= 45) {
            System.out.println(i);
            String url = "https://book.douban.com/subject_search?search_text=" + key + "&cat=1001&start=" + i;
            driver.get(url);
            Thread.sleep(1000);
            TxtUtil.writeTxt("C:\\Users\\issuser\\Desktop\\tmp11.txt", sb1.toString());
            WebElement element = ((ChromeDriver) driver).findElementById("root");
            sb1.append(element.getText() + "\n");
            i += 15;
        }
        System.out.println("999999999999999999999999999999999");
        TxtUtil.writeTxt("C:\\Users\\issuser\\Desktop\\tmp11.txt", sb1.toString());
        System.out.println(sb1.toString());
    }

    /*第二部数据过滤*/
    @Test
    public void testInfo() {
        String path = "C:\\Users\\issuser\\Desktop\\tmp11.txt";
        List<String> list = TxtUtil.readTxt(path);
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (!str.contains("前页") && str.contains("评价")) {

                String head = list.get(i);
                String comment = list.get(i + 1);
                if (!comment.contains("评价人数不足") && !comment.contains("目前无人评价")
                        && !head.contains("评价人数不足") && !head.contains("目前无人评价")) {
                    sb2.append(list.get(i - 1) + "\t" + head + "\t" + comment + "\n");
                }
            }

        }
        TxtUtil.writeTxt("C:\\Users\\issuser\\Desktop\\tmp11.txt", sb2.toString());
        System.out.println(sb2.toString());
    }

    /*第三部数据替换 排序*/
    @Test
    public void testInfo123() {

        String path = "C:\\Users\\issuser\\Desktop\\tmp11.txt";
        List<String> list = TxtUtil.readTxt(path);
        StringBuilder sb3 = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);

            String[] array = str.split("\t");
            StringBuilder sb = new StringBuilder();
            if (array[0].toLowerCase().contains(key)) {
                sb.append(array[0] + "\t");
                sb.append(array[1].replace("人评价)", "\t").replace("(", "\t"));
                String ss = "";
                if (array[2].contains("出版社")) {
                    String[] tempArray = array[2].split("/");
                    for (int j = 0; j < tempArray.length; j++) {
                        if (tempArray[j].contains("出版社")) {
                            ss = tempArray[j];
                            break;
                        }
                    }
                }
                sb.append(ss);
                sb3.append(sb.toString() + "\n");
            }
        }
        TxtUtil.writeTxt("C:\\Users\\issuser\\Desktop\\tmp11.txt", sb3.toString());
        System.out.println(sb3.toString());
    }

}
