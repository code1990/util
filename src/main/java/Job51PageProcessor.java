import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.net.URLEncoder;
import java.util.List;

public class Job51PageProcessor implements PageProcessor {

    static StringBuilder builder = new StringBuilder("*****************************************\n");
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    public void process(Page page) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Html html = page.getHtml();
        Document document = html.getDocument();
        Element div = document.getElementById("resultList");
        List<Element> list = div.getElementsByClass("el");
        for(int i =1;i<list.size();i++){
            Element element=list.get(i);
            String t1 = element.getElementsByClass("t1").first().text();
            String t2 = element.getElementsByClass("t2").first().text();
            String t3 = element.getElementsByClass("t3").first().text();
            String t4 = element.getElementsByClass("t4").first().text();
            String t5 = element.getElementsByClass("t5").first().text();
            String a = element.getElementsByClass("t1").first().getElementsByTag("a").first().attr("href");
            String tmp = i+"\t"+t1+"\t"+t2+"\t"+t3+"\t"+t4+"\t"+t5+"\t"+a;
            System.out.println(tmp);
            builder.append(tmp+"\n");
        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        for(int i =1;i<=30;i++){
            String path = "https://search.51job.com/list/000000,000000,0000,00,9,06,%25E5%2589%258D%25E7%25AB%25AF,2,"+i+".html?lang=c&stype=1&postchannel=0000&workyear=03&cotype=99&degreefrom=03&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=7&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
            Spider.create(new Job51PageProcessor()).addUrl(path).thread(5).run();
        }

        System.out.println(builder);
    }

}

