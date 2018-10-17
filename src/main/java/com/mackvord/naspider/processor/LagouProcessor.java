package com.mackvord.naspider.processor;

import com.mackvord.naspider.pipeline.LagouMysqlPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.*;

/**
 * @author: mackvord@gmail.com
 * @date: 18-10-2
 * @time: 下午5:23
 */
@Component
public class LagouProcessor implements PageProcessor {

    @Autowired
    private LagouMysqlPipeline lagouMysqlPipeline;

    /**
     * 定义一个标志，避免链接重复添加
     */
    private static boolean flag = true;

    /**
     * 设置站点信息
     */
    private Site site = Site.me()
            .setRetryTimes(5)
            .setSleepTime(2000)
            .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
            .addHeader("Accept-Encoding", "gzip, deflate, br")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
            .addHeader("Connection", "keep-alive")
            // .addHeader("Content-Length", "23")
            .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            .addHeader("Host", "www.lagou.com")
            .addHeader("Origin", "https://www.lagou.com")
            .addHeader("Referer", "https://www.lagou.com/jobs/list_Java?px=default&city=%E5%B9%BF%E5%B7%9E")
            .addHeader("X-Anit-Forge-Code", "0")
            .addHeader("X-Anit-Forge-Token", "None")
            .addHeader("X-Requested-With", "XMLHttpRequest")
            .addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko)" +
                    " Chrome/68.0.3440.84 Safari/537.36")
            .addHeader("Cookie", "_ga=GA1.2.1280051714.1538720507; _gid=GA1.2.378872501.1538720507" +
                    "; user_trace_token=20181005142146-efa1cbc6-c866-11e8-bb68-5254005c3644" +
                    "; LGUID=20181005142146-efa1d0ce-c866-11e8-bb68-5254005c3644" +
                    "; WEBTJ-ID=20181006141809-1664806af9719e-0740f6b307c19b-182e1503-1049088-1664806af98e1" +
                    "; JSESSIONID=ABAAABAABEEAAJAAC617D5D8DA4D5C0C8BBE6E7DF7A4EDD" +
                    "; TG-TRACK-CODE=index_navigation" +
                    "; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1538744240,1538806690,1538806709,1538807934" +
                    "; index_location_city=%E5%B9%BF%E5%B7%9E" +
                    "; LGSID=20181006151619-b8fa5b95-c937-11e8-bb68-5254005c3644" +
                    "; LGRID=20181006154438-ad322db4-c93b-11e8-bb68-5254005c3644" +
                    "; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1538811878" +
                    "; SEARCH_ID=2559e5cdd0714d7b9138df224db8e95a");

    @Override
    public void process(Page page) {
        processHotCity(page);
        lagouMysqlPipeline.encapsulation(page);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void processHotCity(Page page) {
        if (flag) {
            int index = 0;
            Request[] requests = new Request[this.getCity().size() * 30];
            Map<String, Object> map = new HashMap<>();
            // 循环添加请求URL
            while (index < this.getCity().size()) {
                for (int i = 0; i < 30; i++) {
                    requests[(index * 30) + i] = new Request("https://www.lagou.com/jobs/positionAjax.json?px=default&city=" +
                            this.getCity().get(index) + "&needAddtionalResult=false");
                    requests[i].setMethod(HttpConstant.Method.POST);
                    // 设置表单数据、请求体以及将请求添加到抓取队列中
                    if (i == 0) {
                        // 每个城市的第一个访问的URL中的From Data的first值为true
                        map.put("first", "true");
                    } else {
                        map.put("first", "false");
                    }
                    map.put("pn", i + 1);
                    map.put("kd", "java");
                    requests[i].setRequestBody(HttpRequestBody.form(map, "UTF-8"));
                    page.addTargetRequest(requests[i]);
                }
                index ++;
            }
        }
        // 改变标志，不再添加链接
        flag = false;
    }

    public List<String> getCity() {
        List<String> citys = new ArrayList<>();
        // 北京
        citys.add("%E5%8C%97%E4%BA%AC");
        // 上海
        citys.add("%E4%B8%8A%E6%B5%B7");
        // 广州
        citys.add("%E5%B9%BF%E5%B7%9E");
        // 深圳
        citys.add("%E6%B7%B1%E5%9C%B3");
        // 杭州
        citys.add("%E6%9D%AD%E5%B7%9E");
        // 武汉
        citys.add("%E6%AD%A6%E6%B1%89");
        // 成都
        citys.add("%E6%88%90%E9%83%BD");
        // 南京
        citys.add("%E5%8D%97%E4%BA%AC");
        // 西安
        //citys.add("%E8%A5%BF%E5%AE%89");
        // 厦门
        //citys.add("%E5%8E%A6%E9%97%A8");
        // 长沙
        //citys.add("%E9%95%BF%E6%B2%99");
        // 天津
        //citys.add("%E5%A4%A9%E6%B4%A5");
        // 苏州
        //citys.add("%E8%8B%8F%E5%B7%9E");
        return citys;
    }

    public void start(PageProcessor pageProcessor) {
        Spider.create(pageProcessor)
                .addUrl("https://www.lagou.com/jobs/positionAjax.json?px=default&city=%E5%B9%BF%E5%B7%9E" +
                        "&needAddtionalResult=false")
                .thread(5)
                .run();
    }
}
