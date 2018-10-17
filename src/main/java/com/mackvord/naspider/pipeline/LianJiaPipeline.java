package com.mackvord.naspider.pipeline;

import com.mackvord.naspider.dao.LianJiaMapper;
import com.mackvord.naspider.model.LianJia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;

/**
 * @author: mackvord@gmail.com
 * @date: 18-10-15
 * @time: 下午10:23
 */
@Component
public class LianJiaPipeline {

    @Autowired
    private LianJiaMapper lianJiaMapper;

    public void encapsulation(Page page) {
        // 描述
        String descripton = page.getHtml().xpath("//h1[@class='main']/text()").get();
        // 价格
        String price = page.getHtml().xpath("//div[@class='content zf-content']/div/span[@class='total']/text()").get();
        // 面积
        String area = page.getHtml().xpath("//div[@class='zf-room']/p[1]/text()").get();
        // 房屋户型
        String houseType = page.getHtml().xpath("//div[@class='zf-room']/p[2]/text()").get();
        // 楼层
        String floor = page.getHtml().xpath("//div[@class='zf-room']/p[3]/text()").get();
        // 房屋朝向
        String direction = page.getHtml().xpath("//div[@class='zf-room']/p[4]/text()").get();
        // 地铁
        String subway = page.getHtml().xpath("//div[@class='zf-room']/p[5]/text()").get();
        // 居住区
        String residentialArea = page.getHtml().xpath("//div[@class='zf-room']/p[6]/a/text()").get();
        // 地理位置
        String location = page.getHtml().xpath("//div[@class='zf-room']/p[7]/a/text()").get();
        // 发布时间
        String releaseTime = page.getHtml().xpath("//div[@class='zf-room']/p[8]/text()").get();

        LianJia lianJia = new LianJia();
        lianJia.setDescription(descripton);
        lianJia.setPrice(Double.valueOf(price));
        lianJia.setArea(area);
        lianJia.setHouseType(houseType);
        lianJia.setFloor(floor);
        lianJia.setDirection(direction);
        lianJia.setSubway(subway);
        lianJia.setResidentialArea(residentialArea);
        lianJia.setLocation(location);
        lianJia.setReleaseTime(releaseTime);
        lianJiaMapper.save(lianJia);
    }
}
