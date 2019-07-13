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
 */
public class CET4Word implements PageProcessor {

    static StringBuilder builder = new StringBuilder("*****************************************\n");
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    public void process(Page page) {
        Html html = page.getHtml();
        Document document = html.getDocument();
        Element div = document.getElementsByClass("content-wrap").first();
        StringBuilder sbs = new StringBuilder();
        List<Element> list = div.getElementsByClass("word-box").first().getElementsByClass("word");
        for (int i = 0; i < list.size(); i++) {
            int index = (i+1);
            String title = list.get(i).text().trim();
            String href = "https://www.koolearn.com"+list.get(i).attr("href").trim();
            sbs.append(index+"\t"+title+"\t"+href+"\n");
        }
        builder.append(sbs.toString()+"\n=======\n");
//        System.out.println(builder.toString());
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
//        String[] array = {
//                "http://cet4.koolearn.com/20181224/827907.html",
//                "http://cet4.koolearn.com/20181224/827908.html",
//                "http://cet4.koolearn.com/20181224/827909.html",
//                "http://cet4.koolearn.com/20181224/827910.html",
//                "http://cet4.koolearn.com/20190114/827943.html",
//                "http://cet4.koolearn.com/20190114/827944.html",
//                "http://cet4.koolearn.com/20190114/827945.html",
//                "http://cet4.koolearn.com/20190114/827946.html",
//                "http://cet4.koolearn.com/20190116/827975.html",
//                "http://cet4.koolearn.com/20190116/827976.html",
//                "http://cet4.koolearn.com/20190116/827977.html",
//                "http://cet4.koolearn.com/20190116/827978.html",
//                "http://cet4.koolearn.com/20190117/828010.html",
//                "http://cet4.koolearn.com/20190117/828011.html",
//                "http://cet4.koolearn.com/20190117/828012.html",
//                "http://cet4.koolearn.com/20190117/828013.html",
//                "http://cet4.koolearn.com/20190118/828042.html",
//                "http://cet4.koolearn.com/20190118/828043.html",
//                "http://cet4.koolearn.com/20190118/828044.html",
//                "http://cet4.koolearn.com/20190118/828045.html",
//                "http://cet4.koolearn.com/20190121/828073.html",
//                "http://cet4.koolearn.com/20190121/828074.html",
//                "http://cet4.koolearn.com/20190121/828075.html",
//                "http://cet4.koolearn.com/20190121/828076.html"
//        };

//        for (int j=0;j<array.length;j++) {
//            String str = array[j];
//            for (int i = 0; i <8 ; i++) {
//                String url = "";
//                if(i==0){
//                    url = str;
//                }else{
//                    url = str.replace(".html","")+"_"+i+".html";
//                }
//                Spider.create(new CET4Word()).addUrl(url).thread(5).run();
//            }
//
//        }
        for(int i =1;i<=25;i++){
            String url = "https://www.koolearn.com/dict/tag_939_"+i+".html";
            System.out.println(url);
            Spider.create(new CET4Word()).addUrl(url).thread(5).run();
        }

        System.out.println(builder);
    }

    @Test
    public void testInfo(){
        List<String> list = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\cet20190708.txt");
        Map<String,String> map = new HashMap<String, String>();
        for(String str:list){
            map.put(str.split("\t")[1].toLowerCase(),str.split("\t")[2]);
        }
        Object[] array = map.keySet().toArray();
        Arrays.sort(array);
        for(int i =0;i<array.length;i++){
            String obj = array[i].toString();
            System.out.println((i+1)+"\t"+obj+"\t"+map.get(obj));
        }
        System.out.println("======================");
        List<String> list2 = TxtUtil.readTxt("C:\\Users\\issuser\\Desktop\\cet2019.txt");
        Map<String,String> map2 = new HashMap<String, String>();
        for(String str:list2){
            map2.put(str.split("\t")[1].toLowerCase(),str.split("\t")[2]);
        }
        Object[] array2 = map2.keySet().toArray();
        Arrays.sort(array2);
        for(int i =0;i<array2.length;i++){
            String obj = array2[i].toString();
            if(null == map.get(obj)){
                System.out.println(obj+"\t"+map2.get(obj));
            }
        }

    }

    @Test
    public void getInfo123(){
        for(int i=1;i<=4176;i++){
            System.out.println(i);
        }
        System.out.println();
    }
}

