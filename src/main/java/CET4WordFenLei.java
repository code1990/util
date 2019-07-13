import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author issuser
 * @date 2019-06-26 14:15
 * 考研词汇书词汇表
 */
public class CET4WordFenLei implements PageProcessor {

    static StringBuilder builder = new StringBuilder("*****************************************\n");
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    public void process(Page page) {
        Html html = page.getHtml();
        Document document = html.getDocument();
        Element div = document.getElementsByClass("left-content").first();
        StringBuilder sbs = new StringBuilder();
        List<Element> list = div.getElementsByClass("word-title");
        for (int i = 0; i < list.size(); i++) {
            int index = (i + 1);
            String title = list.get(i).text();
            String href = "https://www.koolearn.com" + list.get(i).getElementsByTag("a").attr("href").trim();
            sbs.append(index + "\t" + title + "\t" + href + "\n");
        }
        builder.append(sbs.toString() + "\n=======\n");
//        System.out.println(builder.toString());
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 30; i++) {
            String url = "https://www.koolearn.com/dict/fenlei_2_103_" + i + ".html";
            System.out.println(url);
            Spider.create(new CET4WordFenLei()).addUrl(url).thread(5).run();
        }

        System.out.println(builder);
    }

}