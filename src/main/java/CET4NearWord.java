import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author issuser
 * @date 2019-07-05 10:45
 */
public class CET4NearWord implements PageProcessor {

    static StringBuilder builder = new StringBuilder("*****************************************\n");
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    public void process(Page page) {
        Html html = page.getHtml();
        Document document = html.getDocument();
        StringBuilder stringBuilder = new StringBuilder("");
        String word =document.getElementsByClass("word-title").first().getElementsByClass("word-spell").text();
        stringBuilder.append(word+":");
        String content = "";
        if(3==document.getElementsByClass("details-content").size()){
            StringBuilder tmp = new StringBuilder("");
            for(Element e:document.getElementsByClass("details-content").get(1).getElementsByClass("clearfix")){
                String adj = e.text().trim();
                tmp.append(adj+";");
            }
            tmp.append("\n");
            content = tmp.toString();
        }

        stringBuilder.append(content);
        builder.append(stringBuilder);
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\1234567.txt");
        for (int j=0;j<list.size();j++) {
            int index = 200+j+1;
            String str = list.get(j);
            if ("".equals(str.trim()) || null == str.trim()) {
                builder.append(index+"\t"+""+"\n");
            } else {
                builder.append(index+"\t"+"\"");
                String[] array = str.split(",");
                for (String ss : array) {
                    Spider.create(new CET4NearWord()).addUrl(ss).thread(5).run();
                }
                builder.append("\"\n");
            }
        }
        System.out.println(builder.toString().replaceAll("\"\"","\"\n\""));
    }

    @Test
    public void testIfno123(){
        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\abc.txt");
        for (int j=0;j<list.size();j++) {
            String str = list.get(j);
            if("".equals(str.trim()) || null == str){
                System.out.println(str);
            }else{
                System.out.println(splitStr(str));
            }

        }
    }

    public static String splitStr(String str){
        String result="";
        int index = 0;
        for(int i =0;i<str.split("").length;i++){
            String ss = str.split("")[i];
            if(!ss.trim().equals("")&& !ss.trim().equals("\"") && !Pattern.matches("^[a-zA-Z]*$", ss)){
                index = i;
                break;
            }
        }
        if (index!=0){
            result = str.substring(0,index);
        }else {
            result = str;
        }
        return result;
    }

    @Test
    public void testInfo1233(){
        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\bcd.txt");
        String str = list.get(0);
        System.out.println(list.size());
//        for (int j=202;j<=300;j++) {
//            System.out.println();
//        }
    }
}