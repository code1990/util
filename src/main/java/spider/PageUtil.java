package spider;

import log.SysLog;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
//import us.codecraft.webmagic.samples.scheduler.PageTagType;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {
    private static SysLog logger = SysLog.getInstance();
    public static List<String> getUrl(Page page,PageTagType tagType,String tagName){
        List<String> list = new ArrayList<String>();
        Elements elements = getElements(page,tagType,tagName);
        for(Element element:elements){
            String url = element.getElementsByTag("a").attr("href").trim().toString();
            list.add(url);
        }
        logger.warn(list.size()+"");
        return list;
    }

    public static String getText(Page page,PageTagType tagType,String tagName){
        String text = "";
        Elements elements = getElements(page,tagType,tagName);
        if (elements != null && !elements.isEmpty()) {
            text = elements.first().text().trim().toString();
        }
        return text;
    }

    private static Elements getElements(Page page,PageTagType tagType,String tagName){
        Html html = page.getHtml();
        Document document = html.getDocument();
        Elements elements = null;
        if (PageTagType.className.equals(tagType)){
            elements= document.getElementsByClass(tagName);
        }else if (PageTagType.tagName.equals(tagType)){
            elements= document.getElementsByTag(tagName);
        }else if(PageTagType.attributeName.equals(tagType)){
            elements= document.getElementsByAttribute(tagName);
        }
        return elements;
    }
}
