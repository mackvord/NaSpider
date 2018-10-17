package com.mackvord.naspider.processor;

import com.mackvord.naspider.pipeline.LianJiaPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author: mackvord@gmail.com
 * @date: 18-10-15
 * @time: 下午10:23
 */
@Component
public class LianJiaProcessor implements PageProcessor {

    @Autowired
    private LianJiaPipeline lianJiaPipeline;

    private Site site = Site.me().setSleepTime(500).setRetryTimes(3);

    @Override
    public void process(Page page) {
        /*if (page.getUrl().regex("https://gz\\.lianjia\\.com/zufang/[a-z]+").match()) {
            String houseCountRegex = "//div[@class='page-box house-lst-page-box']/@page-data";
            if (page.getHtml().xpath(houseCountRegex).get() != null) {
                JSONObject houseCountJson = JSON.parseObject(page.getHtml().xpath(houseCountRegex).get());
                String currentUrl = page.getUrl().toString() + "/pg";
                for (int i = 0; i < Integer.valueOf(houseCountJson.get("totalPage").toString()); i++) {
                    // 分页链接
                    page.addTargetRequest(currentUrl + String.valueOf(i + 1));
                }
            }
        } else {
            String str = "https://gz\\.lianjia\\.com/zufang/\\d+\\.html";
            page.addTargetRequests(page.getHtml().xpath("//div[@class='pic-panel']").links().regex(str).all());
        }*/
        String URL_LIST = "https://gz\\.lianjia\\.com/zufang/\\w+/pg\\d+";
        String URL_POST = "https://gz\\.lianjia\\.com/zufang/\\d+.html";
        if (page.getUrl().regex(URL_LIST).match()) {
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
            page.addTargetRequests(page.getHtml().xpath("//div[@class='info-panel']").links().regex(URL_POST).all());
        } else {
            lianJiaPipeline.encapsulation(page);
        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    private String[] getStartUrls() {
        String[] urls = new String[]{
                "https://gz.lianjia.com/zufang/tianhe/pg1"
                , "https://gz.lianjia.com/zufang/yuexiu/pg1"
                , "https://gz.lianjia.com/zufang/liwan/pg1"
                , "https://gz.lianjia.com/zufang/haizhu/pg1"
                , "https://gz.lianjia.com/zufang/panyu/pg1"
                , "https://gz.lianjia.com/zufang/baiyun/pg1"
                , "https://gz.lianjia.com/zufang/huangpugz/pg1"
                , "https://gz.lianjia.com/zufang/conghua/pg1"
                , "https://gz.lianjia.com/zufang/zengcheng/pg1"
                , "https://gz.lianjia.com/zufang/huadou/pg1"
                , "https://gz.lianjia.com/zufang/nansha/pg1"
        };
        return urls;
    }

    public void start(PageProcessor pageProcessor) {
        Spider.create(pageProcessor).addUrl(getStartUrls()).thread(5).run();
    }
}
