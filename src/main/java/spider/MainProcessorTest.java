package spider;

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

public class MainProcessorTest   {

       public static void main(String[] args) {
           MainProcessor t = new MainProcessor();
           t.run();
           System.out.println(Arrays.toString(t.getBlogContentList().toArray()));
    }
}
