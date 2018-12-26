package spider.jb51;

import io.TxtUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import spider.RootSpider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Spider03 extends RootSpider {
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

        Element uList = document.getElementById("list_ul_more");
        int length = uList.getElementsByTag("li").size();
        if(length != 0){
            Elements lis = uList.getElementsByTag("li");
            for (int i = 0; i < lis.size(); i++) {
                Element a = lis.get(i).getElementsByTag("a").first();
                String href = mainUrl+a.attr("href");
                String text = a.text();
                System.out.println(text+";"+href);
                sb.append(text+";"+href+"\n");
            }
        }
    }

    @Override
    public void run() {
        List<String> list = TxtUtil.readTxt("E:\\temp\\51jb\\02.txt");
        PrintStream out = System.out;
        try {
            /*//写好输出位置文件；*/
            PrintStream print=new PrintStream("E:\\temp\\51jb\\03temp.txt");
            System.setOut(print);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(String str:list){
            String url = str.trim();
            us.codecraft.webmagic.Spider.create(new Spider03()).addUrl(url).thread(10).run();
        }
        System.setOut(out);
        TxtUtil.writeTxt("E:\\temp\\51jb\\03.txt", sb.toString());
        List<String> lessList = new ArrayList<>();
        while (true){
            String readStr = Arrays.toString(TxtUtil.readTxt("E:\\temp\\51jb\\03temp.txt").toArray());
            for(String str:list){
                String url = str.trim();
                if(!readStr.contains(url)){
                    lessList.add(url);
                }
            }
            if(0 == lessList.size()){
                TxtUtil.writeTxt("E:\\temp\\51jb\\03less.txt", sb.toString());
                break;
            }else {
                try {
                    /*//写好输出位置文件；*/
                    PrintStream print=new PrintStream("E:\\temp\\51jb\\03temp.txt");
                    System.setOut(print);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                for(String str:lessList){
                    String url = str.trim();
                    us.codecraft.webmagic.Spider.create(new Spider03()).addUrl(url).thread(10).run();
                }
                System.setOut(out);
            }
            continue;
        }

    }
}
