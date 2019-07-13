import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class AutoBook {

    public static void main(String[] args) {
        //要调起新版本的firefox，需要geckodriver驱动（未设置时java.lang.IllegalStateException报错）
        System.setProperty("webdriver.gecko.driver", "D:\\sdk\\driver\\geckodriver.exe");
        //若无法打开Firefox浏览器，可设定Firefox浏览器的安装路径(未设置路径时path报错)
        System.setProperty("webdriver.firefox.bin", "D:\\sdk\\Mozilla Firefox\\firefox.exe");
        //打开Firefox浏览器
        WebDriver driver = new FirefoxDriver();

        String url = "http://www.duxiu.com/login.jsp?backurl=&num=&username=&refer=https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&ch=&tn=monline_3_dg&bar=&wd=%E5%9B%BE%E4%B9%A6%E6%A3%80%E7%B4%A2&oq=%25E5%259B%25BE%25E4%25B9%25A6%25E7%259B%25AE%25E5%25BD%2595%25E6%259F%25A5%25E8%25AF%25A2%2520-baijiahao&rsv_pq=e694d253000032df&rsv_t=8202dRAa6tEGR79XNKNiaIYzuZ%2B%2F%2B5LLkOFwoNRusffbz48digQNfeKdSVtmlw7ZTViv&rqlang=cn&rsv_enter=1&inputT=1894&flag=true";
        driver.get(url);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.manage().addCookie(new Cookie("search_uuid", "32cec375%2d8033%2d4bf1%2d9de4%2db39721cc340d"));
        driver.manage().addCookie(new Cookie("route", "0c4d9c13d4e23f9d1506eceb3f9fa76c"));
        driver.manage().addCookie(new Cookie("cookiecheck", "true"));
        driver.manage().addCookie(new Cookie("duxiu", "userName%5fdsr%2c%3dshr%2c%21userid%5fdsr%2c%3d10321%2c%21char%5fdsr%2c%3d%2c%21metaType%2c%3d0%2c%21dsr%5ffrom%2c%3d1%2c%21logo%5fdsr%2c%3dunits%5flogo%2flogo%5fexp%2ejpg%2c%21logosmall%5fdsr%2c%3dunits%5flogo%2flogosmall%5fexp%2ejpg%2c%21title%5fdsr%2c%3d%u8bfb%u79c0%u4f53%u9a8c%u7248%2c%21url%5fdsr%2c%3d%2c%21compcode%5fdsr%2c%3d%2c%21province%5fdsr%2c%3d%u5176%u5b83%2c%21readDom%2c%3d0%2c%21isdomain%2c%3d3%2c%21showcol%2c%3d0%2c%21hu%2c%3d0%2c%21areaid%2c%3d0%2c%21uscol%2c%3d0%2c%21isfirst%2c%3d2%2c%21istest%2c%3d1%2c%21cdb%2c%3d0%2c%21og%2c%3d0%2c%21ogvalue%2c%3d0%2c%21testornot%2c%3d0%2c%21remind%2c%3d0%2c%21datecount%2c%3d3883%2c%21userIPType%2c%3d1%2c%21lt%2c%3d1%2c%21ttt%2c%3dduxiu%2c%21enc%5fdsr%2c%3dEF91601FC5ED190E18C9A93A24DA8E87"));
        driver.manage().addCookie(new Cookie("AID_dsr", "3946"));
        driver.manage().addCookie(new Cookie("superlib", "\"\""));
        driver.manage().addCookie(new Cookie("DSSTASH_LOG", "C%5f1%2dUN%5f3946%2dUS%5f0%2dT%5f1557882051160"));
        driver.manage().addCookie(new Cookie("fresh_dsr", "1557983859681"));
        driver.manage().addCookie(new Cookie("testcookie", "yes"));
        driver.navigate().refresh();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String url1 = "http://www.duxiu.com/login.jsp?backurl=&num=&username=&refer=https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&ch=&tn=monline_3_dg&bar=&wd=%E5%9B%BE%E4%B9%A6%E6%A3%80%E7%B4%A2&oq=%25E5%259B%25BE%25E4%25B9%25A6%25E7%259B%25AE%25E5%25BD%2595%25E6%259F%25A5%25E8%25AF%25A2%2520-baijiahao&rsv_pq=e694d253000032df&rsv_t=8202dRAa6tEGR79XNKNiaIYzuZ%2B%2F%2B5LLkOFwoNRusffbz48digQNfeKdSVtmlw7ZTViv&rqlang=cn&rsv_enter=1&inputT=1894&flag=true";
        driver.get(url);
        StringBuilder sb = new StringBuilder();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.manage().deleteCookieNamed("");
        driver.manage().addCookie(new Cookie("search_uuid","32cec375%2d8033%2d4bf1%2d9de4%2db39721cc340d"));
        driver.manage().addCookie(new Cookie("msign_dsr","1560918370536"));
        driver.manage().addCookie(new Cookie("route","0c4d9c13d4e23f9d1506eceb3f9fa76c"));
        driver.manage().addCookie(new Cookie("testcookie","yes"));
        driver.manage().addCookie(new Cookie("cookiecheck","true"));
        driver.manage().addCookie(new Cookie("duxiu","userName%5fdsr%2c%3dshr%2c%21userid%5fdsr%2c%3d10321%2c%21char%5fdsr%2c%3d%2c%21metaType%2c%3d0%2c%21dsr%5ffrom%2c%3d1%2c%21logo%5fdsr%2c%3dunits%5flogo%2flogo%5fexp%2ejpg%2c%21logosmall%5fdsr%2c%3dunits%5flogo%2flogosmall%5fexp%2ejpg%2c%21title%5fdsr%2c%3d%u8bfb%u79c0%u4f53%u9a8c%u7248%2c%21url%5fdsr%2c%3d%2c%21compcode%5fdsr%2c%3d%2c%21province%5fdsr%2c%3d%u5176%u5b83%2c%21readDom%2c%3d0%2c%21isdomain%2c%3d3%2c%21showcol%2c%3d0%2c%21hu%2c%3d0%2c%21areaid%2c%3d0%2c%21uscol%2c%3d0%2c%21isfirst%2c%3d2%2c%21istest%2c%3d1%2c%21cdb%2c%3d0%2c%21og%2c%3d0%2c%21ogvalue%2c%3d0%2c%21testornot%2c%3d0%2c%21remind%2c%3d0%2c%21datecount%2c%3d3849%2c%21userIPType%2c%3d1%2c%21lt%2c%3d1%2c%21ttt%2c%3dduxiu%2c%21enc%5fdsr%2c%3d8B6357558F4F48A75B0E29227F93B46A"));
        driver.manage().addCookie(new Cookie("AID_dsr","3946"));
        driver.manage().addCookie(new Cookie("superlib","\"\""));
        driver.manage().addCookie(new Cookie("DSSTASH_LOG","C%5f1%2dUN%5f3946%2dUS%5f0%2dT%5f1557882051160"));
        driver.manage().addCookie(new Cookie("fresh_dsr",System.currentTimeMillis()+""));
        driver.manage().addCookie(new Cookie("UM_distinctid","16b6e0d734751-01383081e35935-14397640-100200-16b6e0d73481b1"));

        String title = driver.getTitle();
        System.out.println(title);
        Actions action = new Actions(driver);
        // 鼠标右键点击指定的元素
//        action.contextClick(driver.findElement(By.className("leftF"))).click();
        action.click(driver.findElement(By.className("leftF")));
    }

}
