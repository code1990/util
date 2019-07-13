package spider.work;
import io.TxtUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

public class Job51DetailPageProcessor implements PageProcessor {

    static StringBuilder builder = new StringBuilder("*****************************************\n\n");
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Html html = page.getHtml();
        Document document = html.getDocument();
        Element div = document.getElementsByClass("tCompany_main").first();
        List<Element> list = div.getElementsByClass("tBorderTop_box").first().getElementsByTag("p");
        StringBuilder sb = new StringBuilder();
        for(int i =1;i<list.size();i++){
            Element p=list.get(i);
            sb.append(p.text()+"\n");
        }
        List<Element> list2 = div.getElementsByClass("tBorderTop_box").first().getElementsByTag("div");
        for(int i =1;i<list2.size();i++){
            Element p=list2.get(i);
            sb.append(p.text()+"\n");
        }
        builder.append(sb.toString().replace("微信分享","##############")+"\n");
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        List<String > list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\tmp.txt");
        for(int i =100;i<200;i++){
            String path = list.get(i);
            Spider.create(new Job51DetailPageProcessor()).addUrl(path).thread(5).run();
        }

        System.out.println(builder);
    }

}

