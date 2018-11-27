package selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Web自动化测试工具类 可以使用java来模拟浏览器行为 实现对于动态页面内容的爬取
 *
 * @author issuser
 */
public class SeleniumUtil {

    /**
     * 获取一个默认的浏览器创建实例
     *
     * @return
     */
    public static WebDriver getInstance() {
        //chrome浏览器安装路径 32位
        String chromebin = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
        //chromedriver浏览器驱动文件路径 32位
        String chromedriver = "D:\\sdk\\driver\\chromedriver.exe";

        /*  把chrome浏览器的位置设置为JVM全局的系统变量 */
        System.setProperty("webdriver.chrome.bin", chromebin);
        /* 把chrome浏览器的驱动陈旭设置JVM全局的系统变量*/
        System.setProperty("webdriver.chrome.driver", chromedriver);
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    /**
     * 打开浏览器
     */
    @Test
    public void testOpenBrowser() {
        WebDriver driver = SeleniumUtil.getInstance();
        driver.get("http://www.baidu.com");
        try {
            Thread.sleep(6000);
            //获取网页的title属性
            System.out.println(driver.getTitle());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            driver.close();//关闭浏览器
            driver.quit();//退出浏览器
        }
    }

    /**
     * 测试定位元素 获取页面给定的元素
     * <p>
     * Selenium提供了8种定位方式。
     * <p>
     * id
     * name
     * class name
     * tag name
     * link text
     * partial link text
     * xpath
     * css selector
     * 这8种定位方式在Java selenium中所对应的方法为：
     * <p>
     * findElement(By.id())
     * findElement(By.name())
     * findElement(By.className())
     * findElement(By.tagName())
     * findElement(By.linkText())
     * findElement(By.partialLinkText())
     * findElement(By.xpath())
     * findElement(By.cssSelector())
     */
    @Test
    public void testFindElement() {
        WebDriver driver = SeleniumUtil.getInstance();
        driver.get("http://www.baidu.com");
        try {
            //通过id定位:
            System.out.println(driver.findElement(By.id("kw")));

            //通过name定位:
            System.out.println(driver.findElement(By.name("wd")));

            //通过class name定位:
            System.out.println(driver.findElement(By.className("s_ipt")));

            //通过tag name定位:
            System.out.println(driver.findElement(By.tagName("input")));

            //通过xpath定位，xpath定位有N种写法，这里列几个常用写法:
//            System.out.println(driver.findElement(By.xpath("//*[@id='kw']")));
//            System.out.println(driver.findElement(By.xpath("//*[@name='wd']")));
//            System.out.println(driver.findElement(By.xpath("//input[@class='s_ipt']")));
//            System.out.println(driver.findElement(By.xpath("/html/body/form/span/input")));
//            System.out.println(driver.findElement(By.xpath("//span[@class='soutu-btn']/input")));
//            System.out.println(driver.findElement(By.xpath("//form[@id='form']/span/input")));
//            System.out.println(driver.findElement(By.xpath("//input[@id='kw' and @name='wd']")));

            //通过css定位，css定位有N种写法，这里列几个常用写法:
//            System.out.println(driver.findElement(By.cssSelector("#kw")));
//            System.out.println(driver.findElement(By.cssSelector("[name=wd]")));
//            System.out.println(driver.findElement(By.cssSelector(".s_ipt")));
//            System.out.println(driver.findElement(By.cssSelector("html > body > form > span > input")));
//            System.out.println(driver.findElement(By.cssSelector("span.soutu-btn> input#kw")));
//            System.out.println(driver.findElement(By.cssSelector("form#form > span > input")));

            //接下来，我们的页面上有一组文本链接。
            //通过link text定位:
            System.out.println(driver.findElement(By.linkText("新闻")));
            System.out.println(driver.findElement(By.linkText("hao123")));

            //通过link text定位:
            System.out.println(driver.findElement(By.partialLinkText("新")));
            System.out.println(driver.findElement(By.partialLinkText("hao")));
            System.out.println(driver.findElement(By.partialLinkText("123")));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            driver.close();//关闭浏览器
            driver.quit();//退出浏览器
        }
    }

}
