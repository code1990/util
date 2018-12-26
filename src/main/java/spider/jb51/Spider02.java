package spider.jb51;

import io.TxtUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import spider.RootSpider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class Spider02 extends RootSpider {
    private static final String doorUrl = "https://www.jb51.net/books/";
    private static final String mainUrl = "https://www.jb51.net";
    public static StringBuilder sb = new StringBuilder(10000);
    @Override
    public void process(Page page) {
        /*获取一个html*/
        Html html = page.getHtml();
        String url = page.getUrl().toString();
        /*获取一个dom树*/
        Document document = html.getDocument();

        Element plist = document.getElementsByClass("plist").first();
        int length = plist.getElementsByTag("a").size();
        if(length == 0){
            sb.append(url+"\n");
            System.out.println("0");
        }else{
            System.out.println("1");
            String endHref = plist.getElementsByTag("a").last().attr("href");
            String[] pageArray = endHref.replaceAll(".html","").split("_");
            int pageSize = new Integer(pageArray[1]);
            String header = pageArray[0];
            for (int i = 1; i <= pageSize; i++) {
                String mx = doorUrl+header+"_"+i+".html";
                sb.append(mx+"\n");
            }
        }
    }

    @Override
    public void run() {
        List<String> list = TxtUtil.readTxt("E:\\temp\\51jb\\01.txt");
        System.out.println();
        PrintStream out = System.out;
        try {
            PrintStream print=new PrintStream("E:\\temp\\51jb\\02temp.txt");  //写好输出位置文件；
            System.setOut(print);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(String str:list){
            String url = str.split("@")[0].trim();
            us.codecraft.webmagic.Spider.create(new Spider02()).addUrl(url).thread(10).run();
        }
        System.setOut(out);
        String readStr = Arrays.toString(TxtUtil.readTxt("E:\\temp\\51jb\\02temp.txt").toArray());
        for(String str:list){
            String url = str.split("@")[0].trim();
            if(!readStr.contains(url)){
                System.out.println(str);
                sb.append(url+"\n");
            }
        }
        TxtUtil.writeTxt("E:\\temp\\51jb\\02.txt", sb.toString());
    }
}
