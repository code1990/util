package spider.ebook;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.net.URLEncoder;

public class DouBanBookPageProcessor implements PageProcessor {

    static StringBuilder builder = new StringBuilder("*****************************************\n");
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
        Element ul = document.getElementsByClass("subject-list").first();
//        System.out.println(ul.toString());
//        for (int i = 0; i < ul.getElementsByTag("li").size(); i++) {
//            System.out.println();
//        }
        StringBuilder sbs = new StringBuilder();
        for (int i = 0; i < ul.getElementsByTag("li").size(); i++) {
            int index = (i+1);
            String title = ul.getElementsByTag("li").get(i).getElementsByTag("h2").text().trim().replace(" : ","");
            String author = ul.getElementsByTag("li").get(i).getElementsByClass("pub").text().trim().split("/")[0];
            String info = ul.getElementsByClass("star clearfix").eq(i).text().trim();
            String rate = "";
            String person = "";
            if("(少于10人评价)".equals(info) || "(目前无人评价)".equals(info)){
                rate = "0";
                person = "0";
            }else{
                if(info.contains(" ")){
                    rate = info.split("\\ ")[0];
                    person = info.split("\\ ")[1].replace("(","").replace(")","").replace("人评价","");
                }else{
                    rate = "0";
                    person = info.replace("(","").replace(")","").replace("人评价","");
                }

            }
            String url = ul.getElementsByTag("a").eq(i).attr("href");
            String tests = index+"\t"+title+"\t"+author+"\t"+rate+"\t"+person+"\t"+url;
            System.out.println(index+"\t"+title+"\t"+author+"\t"+rate+"\t"+person+"\t"+url);
            sbs.append(tests+"\n");
        }
        builder.append(sbs.toString()+"\n=======\n");
//        System.out.println(sbs.toString());
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
        int x =0;
        while(x<=980){
            String path = "https://book.douban.com/tag/%E6%96%B9%E6%B3%95%E8%AE%BA?start="+x+"&type=T";
            Spider.create(new DouBanBookPageProcessor()).addUrl(path).thread(5).run();
            x+=20;
        }
//        for (int i = 204; i <=229 ; i++) {
//
//        }

//        }
        System.out.println(builder);
    }

    @Test
    public void testInfo() throws Exception{
        String str = "方法论";
        String strGBK = URLEncoder.encode(str, "GBK");
        System.out.println(strGBK);
        String strUTF8 = URLEncoder.encode(str, "UTF-8");
        System.out.println(strUTF8);
    }
}

