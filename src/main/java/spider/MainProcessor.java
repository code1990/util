package spider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
//import us.codecraft.webmagic.samples.scheduler.PageTagType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 */
public class MainProcessor implements PageProcessor,Runnable   {

    private String pageSize;

    private String blogTitleClass;

    private String blogContentClass;

    private String listRegex;

    private String listClass;

    private String domainInfo;

    private List<String> blogContentList;

    public List<String> getBlogContentList() {
        return blogContentList;
    }

    public void setBlogContentList(List<String> blogContentList) {
        this.blogContentList = listInfo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getBlogTitleClass() {
        return blogTitleClass;
    }

    public void setBlogTitleClass(String blogTitleClass) {
        this.blogTitleClass = blogTitleClass;
    }

    public String getBlogContentClass() {
        return blogContentClass;
    }

    public void setBlogContentClass(String blogContentClass) {
        this.blogContentClass = blogContentClass;
    }

    public String getListRegex() {
        return listRegex;
    }

    public void setListRegex(String listRegex) {
        this.listRegex = listRegex;
    }

    public String getListClass() {
        return listClass;
    }

    public void setListClass(String listClass) {
        this.listClass = listClass;
    }

    private static final String URL_LIST = "https://blog.csdn.net/myvanguard/article/list/\\d";

    public static final List<String> listInfo = new ArrayList<String>();

    private Site site = Site
            .me()
//            .setDomain("blog.csdn.net")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        //列表页
        if (page.getUrl().regex(URL_LIST).match()) {
            List<String> list = PageUtil.getUrl(page, PageTagType.className,"article-item-box csdn-tracking-statistics");
            for(String str:list){
                page.addTargetRequest(new Request().setUrl(str));
            }
            //文章页
        } else {
            JSONObject obj = new JSONObject();
            obj.put("title",PageUtil.getText(page, PageTagType.className,"title-article"));
            obj.put("content",PageUtil.getText(page, PageTagType.className,"markdown_views prism-atom-one-dark"));
            listInfo.add(obj.toJSONString());
//            blogContentList.add(obj.toJSONString());
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(
                new MainProcessor())
                .addUrl("https://blog.csdn.net/myvanguard/article/list/1")
                .addPipeline(new ConsolePipeline())
                .run();
        System.out.println(Arrays.toString(listInfo.toArray()));
    }

    @Override
    public void run() {
        Spider.create(
                new MainProcessor())
                .addUrl("https://blog.csdn.net/myvanguard/article/list/1")
                .addPipeline(new ConsolePipeline())
                .run();
        this.setBlogContentList(listInfo);
    }
}
