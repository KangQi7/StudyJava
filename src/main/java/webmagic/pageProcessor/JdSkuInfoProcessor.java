package webmagic.pageProcessor;

import org.jsoup.nodes.Document;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * @Author KangQi
 * @Date 2020/2/20 16:04
 * @Version 1.0
 */
public class JdSkuInfoProcessor implements PageProcessor {
    // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        Document document = html.getDocument();
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String args[]){
        Spider.create(new JdSkuInfoProcessor())
                .addUrl("https://item.jd.com/100002355147.html")
                .thread(5).run();
    }
}
