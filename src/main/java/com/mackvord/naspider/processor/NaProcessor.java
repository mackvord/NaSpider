package com.mackvord.naspider.processor;

import com.mackvord.naspider.model.Video;
import com.mackvord.naspider.pipeline.NaMysqlPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author: mackvord@gmail.com
 * @date: 18-10-3
 * @time: 上午11:06
 */
@Component
public class NaProcessor implements PageProcessor {


    @Autowired
    private NaMysqlPipeline naMysqlPipeline;

    /**
     * 设置站点信息
     */
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    /**
     * 初始URL地址
     */
    private static String startUrl = "http://navod.scse.com.cn/nn_cms/data/template/100000/200003/index_v3_001.php" +
            "?nns_template_type=100000&nns_template_id=200003&nns_user_id=g%2C172.16.146.247%2C5bb18ba8215029f" +
            "&nns_tag=31&nns_media_asset_id=movies&nns_parent_category_id=" +
            "&nns_category_id=1000023&nns_page_name=movie";

    /**
     * 匹配所有子分类链接的正则
     */
    private static String categoryRegex = "http://navod\\.scse\\.com\\.cn/nn_cms/data/template/100000/200003/index_v3_001\\.php" +
            "\\?nns_template_type=100000&nns_template_id=200003&nns_user_id" +
            "=g%2C((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)%2C(\\w+)" +
            "&nns_tag=31&nns_media_asset_id=(\\w+)&nns_parent_category_id=(\\d*)&nns_category_id=(\\d+)" +
            "&nns_page_name=movie";

    /**
     * 匹配所有子分类页面下分页链接
     */
    private static String paginationRegex = "http://navod\\.scse\\.com\\.cn/nn_cms/data/template/100000/200003/index_v3_001\\.php" +
            "\\?nns_template_type=100000&nns_template_id=200003&nns_user_id" +
            "=g%2C((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)%2C(\\w+)" +
            "&nns_tag=31&nns_media_asset_id=(\\w+)&nns_category_id=(\\d+)&nns_parent_category_id=(\\d+)" +
            "&nns_page_name=(\\w+)&nns_page_num=(\\d+)";

    @Override
    public void process(Page page) {
        // 获取当前请求的URL地址，避免页面重复添加次URL到请求队列
        String startUrl = page.getUrl().toString();
        // 获取所有的链接
        List<String> linkList = page.getHtml().links().all();
        for (String link : linkList) {
            if (!startUrl.equals(link)) {
                if (link.matches(categoryRegex) || link.matches(paginationRegex)) {
                    // 将所有符合正则的链接添加到请求队列中
                    page.addTargetRequest(link);
                }
            }
        }

        // 每个页面所属的分类是唯一的
        String category = page.getHtml()
                .xpath("//div[@class='programTop']/h1[@style='color: #08a3db;margin-left: 48px;']/text()").get();
        List<String> coverList = page.getHtml()
                .xpath("//div[@class='classsifyProgram']/ul/li/a/img[@style]/@src").all();
        List<String> nameList = page.getHtml().xpath("//span[@class='programName']/text()").all();
        List<String> urlList = page.getHtml().xpath("//div[@class='classsifyProgram']/ul/li/a/@href").all();

        // 封装Video对象，此处的循环变量也可以用coverList或者urlList的长度，这三者长度是一样的
        for (int i = 0; i < nameList.size(); i++) {
            Video video = new Video();
            video.setCategory(category);
            video.setName(nameList.get(i));
            video.setCover(coverList.get(i));
            video.setUrl(urlList.get(i));
            // 封装完成后调用DAO层保存数据
            naMysqlPipeline.save(video);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void start(PageProcessor pageProcessor) {
        Spider.create(pageProcessor).addUrl(startUrl).thread(5).run();
    }
}
