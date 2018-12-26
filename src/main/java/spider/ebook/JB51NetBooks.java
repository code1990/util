package spider.ebook;

import io.TxtUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.net.URLEncoder;
import java.util.*;

/**
 * 抓取微信有关的pdf下载地址
 */
public class JB51NetBooks implements PageProcessor {
    private static final String mainUrl = "https://www.jb51.net";
    private static final String doorUrl = "https://www.jb51.net/books/";
    private static final String sitePath = "E:\\51jb\\";

    private Site site =
            Site.me()
                    .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
                    .setRetryTimes(5).setSleepTime(1000).setTimeOut(10000);

    /*所有的分页*/
    public static List<String> fenleiList = new ArrayList<String>();
    public static List<String> flMxList = new ArrayList<String>();
    //所有的百度云盘连接
    private static Set<String> set2 = null;//new HashSet<String>();

    public static StringBuilder mxSb = new StringBuilder(200000);
    public static boolean flag = false;
    public static boolean flag1 = false;
    public static boolean flag2 = false;
    public static boolean flag3 = false;
    @Override
    public void process(Page page) {
        /*获取一个html*/
        Html html = page.getHtml();
        /*获取一个dom树*/
        Document document = html.getDocument();

        System.out.println("11111111111111111111111111111 获取所有的分类");
        //遍历其中的一些元素
        Element mainDiv = null;

        if(flag){
            mainDiv = document.getElementsByClass("content clearfix").first();
            if(null != mainDiv){
                Elements aList = mainDiv.getElementsByTag("a");
                StringBuilder sb = new StringBuilder(10000);
                for(Element e:aList){
                    String title = e.text();
                    String href = e.attr("href");
                    System.out.println(title+","+mainUrl+href);
                    sb.append(title+";"+mainUrl+href+"\n");
                    fenleiList.add(mainUrl+href);
                }
                try {
                    TxtUtil.writeTxt(sitePath+System.currentTimeMillis()+".txt", sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            mainDiv = document.getElementsByClass("bgf3").first();
            if (mainDiv != null) {
                Element plist = document.getElementsByClass("plist").first();
                String endHref = plist.getElementsByTag("a").last().attr("href");
                System.out.println(endHref);
                String[] pageArray = endHref.replaceAll(".html","").split("_");
                int pageSize = new Integer(pageArray[1]);
                String header = pageArray[0];
                for (int i = 1; i <= pageSize; i++) {
                    String mx = doorUrl+header+"_"+i+".html";
                    mxSb.append(mx+"\n");
                }
            }
        }

        if(flag){

        }

        mainDiv = document.getElementById("content");
        if(null !=mainDiv){
            StringBuilder sb = new StringBuilder();
            String title = document.getElementsByClass("font16").first().text();
            System.out.println(title);
            String desc = document.getElementById("soft-intro").text().trim();
            System.out.println(desc);
            Element ul = document.getElementsByClass("ul_Address").first();
            String href = ul.getElementsByTag("a").first().attr("href");
            System.out.println(href);
            sb.append(title + "\t百度云\n");
            sb.append(desc+"\n");
            sb.append(href);
            try {
                TxtUtil.writeTxt(sitePath+title+".txt", sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




//        Elements elements = ;
//        if (null != elements && !elements.isEmpty()) {
//            for (int i = 0; i < elements.size(); i++) {
//                Element element = elements.get(i);
//                Element a = element.getElementsByTag("a").get(0);
//                String bookName = a.text().toLowerCase();
////                System.out.println(bookName);
////                if (bookName.contains(key1) && bookName.contains(key2)) {
//                if (bookName.contains("")) {
//                    String href = a.attr("href");
//                    mxList.add(href);
////                    System.out.println(href);
//                }
//            }
//        }
///*        Element footer = document.getElementById("pageFooter");
//        if (null != footer) {
//            Elements element3 = footer.getElementsByTag("a");
//            if (null != element3 && !element3.isEmpty()) {
//                //不包含下一页和尾页
//                for (int i = 0; i < element3.size() - 1; i++) {
//                    String href = element3.attr("href");
//                    list.add(siteInfo + href);
//                }
//            }
//
//        }*/
//
//        Elements element3 = document.getElementsByClass("ul_Address");
//        if (null != element3 && !element3.isEmpty()) {
//            Element element = element3.get(0);
//            Element a = element.getElementsByTag("a").get(0);
//            String href = a.attr("href");
//            String h1 = document.getElementsByTag("h1").get(0).text();
//            set2.add(h1 + "\n" + href);
////            System.out.println(h1);
////            System.out.println(href);
////            set3.add(h1);
//        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws Exception {
//        flag = true;
        if(flag){
            Spider.create(new JB51NetBooks()).addUrl(doorUrl).thread(10).run();
            for (String str:fenleiList) {
                Spider.create(new JB51NetBooks()).addUrl(str).thread(10).run();
            }
            try {
                TxtUtil.writeTxt(sitePath+System.currentTimeMillis()+".txt", mxSb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(flag){
            List<String> bList = TxtUtil.readTxt("E:\\51jb\\1542784217950.txt");
            for(String str:bList){
                Spider.create(new JB51NetBooks()).addUrl(str).thread(5).run();
            }
            try {
                TxtUtil.writeTxt(sitePath+System.currentTimeMillis()+".txt", mxSb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<String> list = TxtUtil.readTxt("E:\\51jb\\1542786466878.txt");
        list = TxtUtil.filter(list);//过滤重复数据
        for(String str:list){
            String url = str.split(";")[1].trim().trim();
//           String url = "https://www.jb51.net/books/174.html";
            Spider.create(new JB51NetBooks()).addUrl(url).thread(5).run();
        }

//        List<String> keyList = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\all.txt");
//        StringBuilder sb = new StringBuilder(30000);
//        for (int j = 0; j < keyList.size(); j++) {
//            set2 = new HashSet<String>();
//            mxList = new ArrayList<String>();
//            sb.setLength(0);
//            key1 = keyList.get(j);
//            System.out.println(key1);
//            String key = URLEncoder.encode(key1, "UTF-8") + "+" + URLEncoder.encode(key2, "UTF-8");
//            for (int i = 0; i < 20; i++) {
//                String url = "http://so.jb51.net/cse/search?q=" + key + "&p=" + i + "&s=10520733385329581432&nsid=0";
//                Spider.create(new JB51NetBooks()).addUrl(url).thread(5).run();
////                System.out.println(url);
//            }
//            for (int i = 0; i < mxList.size(); i++) {
//                String url = mxList.get(i);
//                Spider.create(new JB51NetBooks()).addUrl(url).thread(5).run();
////                System.out.println(url);
//            }
//
////            sb.append("\n************************************\n");
//            sb.append(key1 + " pdf 电子书大全 百度云\n");
//            sb.append("请配合浏览器第三方网盘插件，自动获取密码\n");
//            for (String string : set2) {
//                sb.append(string + "\n");
//            }
////            sb.append("\n----------------------------------------------\n");
//
////            sb.append("https://ypsuperkey.meek.com.cn/ (网盘万能钥匙浏览器插件官网）\n");
////            System.out.println(sb.toString());
//            TxtUtil.writeTxt("C:\\Users\\issuser\\Desktop\\ebook123\\" + key1 + ".txt", sb.toString());
//            sb.setLength(0);
//        }
//        System.out.println("-----end------");
//        System.out.println(sb.toString());
    }

    @Test
    public void dealFile(){
        try {
            List<String> list = TxtUtil.readTxt("E:\\51jb\\1542786466878.txt");
            StringBuilder sb = new StringBuilder();
            for(String str:list){
                str = str.replaceAll(".html",".html\n");
                sb.append(str+"\n");
            }
            TxtUtil.writeTxt("E:\\51jb\\1542786466878.txt",sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

