package selenium;
import io.TxtUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class AutoTranslate {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\sdk\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\bakup\\new11.txt");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <1 ; i++) {
            String word = list.get(i);
            sb.append((i+1)+"\t"+word+"\t");
//            driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
            driver.get("http://dict.cn/"+"cargo");
            String title = driver.getTitle();
            System.out.println(i+"\t"+title);
            String str = "";

            try {
                Thread.sleep(1500);
                WebElement div = ((ChromeDriver) driver).findElementByCssSelector(".section.sent");
                List<WebElement> h3List = div.findElements(By.tagName("h3"));
                int index =0;
                for (int j = 0; j < h3List.size(); j++) {
                    WebElement h3 = h3List.get(j);
                    if("词汇搭配".equals(h3.getText().trim())){
                        index = j;
                        break;
                    }
                }
                div.findElements(By.tagName("h3")).get(index).click();
                if(div.findElements(By.tagName("ul")).size()>0){
                    int lens = div.findElements(By.tagName("ul")).size();
                    StringBuilder sbs = new StringBuilder();
                    for(int k=0;k<lens;k++){
                        WebElement uL = div.findElements(By.tagName("ul")).get(k);

                        for (WebElement s:uL.findElements(By.tagName("li"))) {
                            sbs.append(s.getText()+",");
                        }

                    }
                    System.out.println(sbs.toString());
                    sb.append(sbs.toString()+"\t\t");

                }else{
                    sb.append("\t\t");
                }

//                str = sbs.toString();
                if(((ChromeDriver) driver).findElementsByClassName("null").size()!=0){
                    StringBuilder sbs2 = new StringBuilder();
                    for (WebElement s:((ChromeDriver) driver).findElementsByClassName("null")){
                        sbs2.append(s.getText()+",");
                    }
                    sb.append(sbs2.toString()+"\t\n");

                }else{
                    sb.append("\t\n");
                }

//                StringBuilder sbs3 = new StringBuilder();
//                try {
////               .get(1).getText();
//                    int length = ((ChromeDriver) driver).findElementsByClassName("highcharts-series-group").get(0).findElements(By.tagName("path")).size();
//
//                    for (int k = length / 2; k < length - 1; k++) {
//                        ((ChromeDriver) driver).findElementsByClassName("highcharts-series-group").get(0).findElements(By.tagName("path")).get(k).click();
//                        Thread.sleep(1000);
//                        String tmp = ((ChromeDriver) driver).findElementsByClassName("highcharts-container").get(0).findElements(By.tagName("tspan")).get(1).getText();
//                        sbs3.append(tmp + "\t");
//                        if(new Integer(tmp.replaceAll("常用度:","").replaceAll("%","").trim())<15){
//                            break;
//                        }
//                    }
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }finally {
//                    sb.append(sbs3 + "\t\n");
//                }
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                System.out.println(sb.toString());
            }

        }
        System.out.println(sb.toString());

//        driver.close();
    }

}
