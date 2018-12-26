package spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class RootSpider implements PageProcessor,Runnable {
    private Site site =
            Site.me()
                    .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
                    .setRetryTimes(5).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void run() {

    }
}
