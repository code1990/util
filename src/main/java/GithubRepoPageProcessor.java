import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

public class GithubRepoPageProcessor implements PageProcessor {

    static StringBuilder builder = new StringBuilder();
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

//    @Override
    public void process(Page page) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Html html = page.getHtml();
        Document document = html.getDocument();
//        System.out.println(document.toString());
//        String word  = page.getUrl().toString().replaceAll("https://fanyi.baidu.com/#en/zh/","");
//        System.out.println(word);
        Element div = document.getElementsByClass("mcon bt f16").first();
        StringBuilder sbs = new StringBuilder();
        for (int i = 0; i <div.getElementsByTag("a").size() ; i++) {
            String str = div.getElementsByTag("a").eq(i).attr("href");
            sbs.append(str+"\n");
        }
        builder.append(sbs.toString()+"\n=======\n");
//        System.out.println(div.toString());
//        StringBuilder sb = new StringBuilder();
//        if(null !=div){
//             String e = div.text();
//            sb.append(e+"\n");
//        }else{
//            sb.append(""+"\n");
//        }
//        builder.append(sb+"\n----\n");
    }

//    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
//        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\bakup\\word123.txt");
//        for (int i = 0; i < list.size(); i++) {
        for (int i = 204; i <=229 ; i++) {
            String path = "https://danci.911cha.com/lesson_"+i+".html";
            Spider.create(new GithubRepoPageProcessor()).addUrl(path).thread(5).run();
        }

//        }
        System.out.println(builder);
    }
}

