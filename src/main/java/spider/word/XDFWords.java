package spider.word;
import io.TxtUtil;
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
import java.util.regex.Pattern;

/**
 * @author issuser
 * @date 2019-07-03 13:48
 * 考研高频3000词词汇表
 */
public class XDFWords implements PageProcessor {

    static StringBuilder builder = new StringBuilder("111111111111111111111111111\n");
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
        StringBuilder stringBuilder = new StringBuilder();
        String word =document.getElementsByClass("word-title").first().getElementsByClass("word-spell").text();
        stringBuilder.append(word+"\t");
        String desc = "";
        if(2==document.getElementsByClass("details-content-title-box").size()){
            StringBuilder tmp = new StringBuilder("");
            String adj = "";
            if(document.getElementsByClass("details-content-title-box").get(1).getElementsByTag("li").size()>=1){
                adj=document.getElementsByClass("details-content-title-box").get(1).getElementsByTag("li").first().text().trim();
            }
            tmp.append(adj);
            desc = tmp.toString();
        }
        stringBuilder.append(splitStr(desc)+"\t");
        stringBuilder.append(desc+"\t");


        String content = "";
        if(3==document.getElementsByClass("details-content").size()){
            StringBuilder tmp = new StringBuilder("\"");
            for(Element e:document.getElementsByClass("details-content").get(1).getElementsByClass("clearfix")){
                System.out.println(e.text().trim());
                String adj = e.text().trim();
                tmp.append(adj+"\n");
            }
            tmp.append("\"");
            content = tmp.toString();
        }
        stringBuilder.append(content+"\t");


        String nwords = "";
//        String hrefs = "";
        if(document.getElementsByClass("right-content").size()>0&&document.getElementsByClass("right-content").get(0).text().trim().contains("同根词")){
            int index = 0;
            List<Element> list = document.getElementsByClass("right-content").get(0).getElementsByTag("div");
            for(int k =0;k<list.size();k++){
                Element e = list.get(k);
                if("同根词".equals(e.text().trim())){
                    index = k+1;
                    break;
                }
            }
            if(index!=0){
                List<Element> list2 = document.getElementsByClass("right-content").get(0).getElementsByTag("div").get(index).getElementsByTag("a");
                StringBuilder tmp = new StringBuilder("\"");
//                StringBuilder tmp2 = new StringBuilder("");
                for(Element e:list2){
                    String adj = e.text().trim();
//                    String href = "https://www.koolearn.com"+e.attr("href");
                    if(!word.equals(adj)){
                        tmp.append(adj+"\n");
//                        tmp2.append(href+",");
                    }

                }
                tmp.append("\"");
//                tmp2.append("");
                nwords = tmp.toString();
//                hrefs = tmp2.toString();
            }
        }
        stringBuilder.append(nwords+"\n");
//        stringBuilder.append(hrefs+"\n");
        builder.append(stringBuilder.toString());
//        System.out.println(stringBuilder.toString());
//        System.out.println(builder.toString());
    }
    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\1233.txt");
        for (int i = 1000; i <1500 ; i++) {
            builder.append((i+1)+"\t");
//            String path = "https://www.koolearn.com/dict/wd_157605.html";
            String path = list.get(i);
//            System.out.println(path);
            Spider.create(new XDFWords()).addUrl(path).thread(5).run();
        }
        System.out.println(builder);
    }

    @Test
    public void testInfo(){
        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\cet20190708.txt");
        Map<String,String>  map = new HashMap<String, String>();
        String[] array = new String[list.size()];
        for(int i =0;i<1;i++){
            String str = "avert";
            map.put(str.split("\t")[0].toLowerCase(),str.split("\t")[1]);
            array[i]=str.split("\t")[0].toLowerCase();
//            System.out.println(array[i]);
        }
        Arrays.sort(array);
        for(int i =0;i<list.size();i++){
            System.out.println(array[i]+"\t"+map.get(array[i]));
        }
    }

//    public List<String> dealWord(List<String> list,String parent){
//        List<String> result = new ArrayList<String>();
//        for(int j =0;j<list.size();j++){
//            String str = list.get(j);
//            String[] strs = str.replace(parent,"").trim().split("");
//            int x = 0;
//            for (int i = 0; i < strs.length; i++) {
//                String s = strs[i];
//                if(!s.trim().equals("") &&!Pattern.matches("^[a-z]$", s)){
//                    x = i;
//                    break;
//                }
//
//            }
//            String englishWord = str.replace(parent,"").trim().substring(0,x).trim();
//            if(!"".equals(englishWord)){
//                String[] strss = englishWord.split("\\s");
//                for(String xx:strss){
//                    if(xx.length()>4 && allwords.contains(xx) && !xx.contains("self")){
//                        result.add(str);
//                        break;
//                    }
//                }
//
//            }
//        }
//        return result;
//    }

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

//    @Test
//    public void testInfo1(){
//        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\123456.txt");
//        String parent = "abandon";
//        List<String> result = dealWord(list,parent);
//        for(String s:result){
//            System.out.println(s);
//        }
//    }

    @Test
    public void testInfo1123(){
        String str = "https://www.koolearn.com/dict/wd_424.html,https://www.koolearn.com/dict/wd_421.html";
        System.out.println(str.split(",").length);
    }
}
