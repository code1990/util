package spider.ebook;

import io.TxtUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 抓取微信有关的pdf下载地址
 */
public class JB51Net implements PageProcessor {
    private static final String siteInfo = "http://so.jb51.net/cse/";
    public static String key1 = "设计模式";
    private static final String key2 = "pdf";
    private Site site =
            Site.me()
                    .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
                    .setRetryTimes(5).setSleepTime(1000).setTimeOut(10000);
    /*所有的分页*/
    private static List<String> mxList = null;//new ArrayList<String>();
    //所有的百度云盘连接
    private static Set<String> set2 = null;//new HashSet<String>();

    @Override
    public void process(Page page) {
        /*获取一个html*/
        Html html = page.getHtml();
        /*获取一个dom树*/
        Document document = html.getDocument();

        //遍历其中的一些元素
        Elements elements = document.getElementsByClass("result f s0");
        if (null != elements && !elements.isEmpty()) {
            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);
                Element a = element.getElementsByTag("a").get(0);
                String bookName = a.text().toLowerCase();
//                System.out.println(bookName);
//                if (bookName.contains(key1) && bookName.contains(key2)) {
                if (bookName.contains(key1)) {
                    String href = a.attr("href");
                    mxList.add(href);
//                    System.out.println(href);
                }
            }
        }
/*        Element footer = document.getElementById("pageFooter");
        if (null != footer) {
            Elements element3 = footer.getElementsByTag("a");
            if (null != element3 && !element3.isEmpty()) {
                //不包含下一页和尾页
                for (int i = 0; i < element3.size() - 1; i++) {
                    String href = element3.attr("href");
                    list.add(siteInfo + href);
                }
            }

        }*/

        Elements element3 = document.getElementsByClass("ul_Address");
        if (null != element3 && !element3.isEmpty()) {
            Element element = element3.get(0);
            Element a = element.getElementsByTag("a").get(0);
            String href = a.attr("href");
            String h1 = document.getElementsByTag("h1").get(0).text();
            set2.add(h1 + "\n" + href);
//            System.out.println(h1);
//            System.out.println(href);
//            set3.add(h1);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws Exception {
        List<String> keyList = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\all.txt");
        StringBuilder sb = new StringBuilder(30000);
        for (int j = 0; j < keyList.size(); j++) {
            set2 = new HashSet<String>();
            mxList = new ArrayList<String>();
            sb.setLength(0);
            key1 = keyList.get(j);
            System.out.println(key1);
            String key = URLEncoder.encode(key1, "UTF-8") + "+" + URLEncoder.encode(key2, "UTF-8");
            for (int i = 0; i < 20; i++) {
                String url = "http://so.jb51.net/cse/search?q=" + key + "&p=" + i + "&s=10520733385329581432&nsid=0";
                Spider.create(new JB51Net()).addUrl(url).thread(5).run();
//                System.out.println(url);
            }
            for (int i = 0; i < mxList.size(); i++) {
                String url = mxList.get(i);
                Spider.create(new JB51Net()).addUrl(url).thread(5).run();
//                System.out.println(url);
            }

//            sb.append("\n************************************\n");
            sb.append(key1 + " pdf 电子书大全 百度云\n");
            sb.append("请配合浏览器第三方网盘插件，自动获取密码\n");
            for (String string : set2) {
                sb.append(string + "\n");
            }
//            sb.append("\n----------------------------------------------\n");

//            sb.append("https://ypsuperkey.meek.com.cn/ (网盘万能钥匙浏览器插件官网）\n");
//            System.out.println(sb.toString());
            TxtUtil.writeTxt("C:\\Users\\issuser\\Desktop\\ebook123\\" + key1 + ".txt", sb.toString());
            sb.setLength(0);
        }
        System.out.println("-----end------");
        System.out.println(sb.toString());
    }

//    public static String getRandomUserAgent(){
//        //随机获取0或者1
//        int x = (int)Math.round(Math.random());
//        String[] rx =(x==0)?Constant.pcUserAgent:Constant.mobileUserAgent;
//        System.out.println(x);
//        //随机获取指定范围的随机数
//        int num=(int) Math.floor(Math.random()*rx.length);
//        System.out.println(num);
//        return rx[num];
//    }
}

