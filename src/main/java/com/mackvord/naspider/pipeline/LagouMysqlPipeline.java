package com.mackvord.naspider.pipeline;

import com.mackvord.naspider.model.Lagou;
import com.mackvord.naspider.dao.LagouMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.util.List;

/**
 * @author: mackvord@gmail.com
 * @date: 18-10-2
 * @time: 下午5:26
 */
@Component
public class LagouMysqlPipeline {

    @Autowired
    private LagouMapper lagouMapper;

    /**
     * 定义一个标志，不抓取第一次访问的URL
     */
    private static boolean startFlag = false;

    /**
     * 保存数据到数据库的方法
     * @param lagou
     */
    public void save(Lagou lagou) {
        lagouMapper.save(lagou);
    }

    /**
     * 封装数据并保存的方法
     * @param page Page对象
     */
    public void encapsulation(Page page) {
        if (startFlag) {
            // 职位名称列表
            List<String> positionName = new JsonPathSelector("$.content.positionResult.result[*].positionName")
                    .selectList(page.getRawText());

            // 年薪列表
            List<String> salary = new JsonPathSelector("$.content.positionResult.result[*].salary")
                    .selectList(page.getRawText());

            // 工作经验列表
            List<String> workYear = new JsonPathSelector("$.content.positionResult.result[*].workYear")
                    .selectList(page.getRawText());

            // 公司全名列表
            List<String> companyFullName = new JsonPathSelector("$.content.positionResult.result[*].companyFullName")
                    .selectList(page.getRawText());

            // 学历列表
            List<String> degree = new JsonPathSelector("$.content.positionResult.result[*].education")
                    .selectList(page.getRawText());

            // 融资规模列表
            List<String> financeStage = new JsonPathSelector("$.content.positionResult.result[*].financeStage")
                    .selectList(page.getRawText());

            // 行业领域列表
            List<String> industryField = new JsonPathSelector("$.content.positionResult.result[*].industryField")
                    .selectList(page.getRawText());

            // 职位福利列表
            List<String> positionAdvantage = new JsonPathSelector("$.content.positionResult.result[*].positionAdvantage")
                    .selectList(page.getRawText());

            // 城市列表
            List<String> city = new JsonPathSelector("$.content.positionResult.result[*].city")
                    .selectList(page.getRawText());

            // 区域列表，例如天河区
            List<String> district = new JsonPathSelector("$.content.positionResult.result[*].district")
                    .selectList(page.getRawText());

            // 公司规模列表
            List<String> companySize = new JsonPathSelector("$.content.positionResult.result[*].companySize")
                    .selectList(page.getRawText());

            // 封装对象并保存
            for (int i = 0; i < positionName.size(); i++) {
                Lagou lagou = new Lagou();
                lagou.setPositionName(positionName.get(i));
                lagou.setSalary(salary.get(i));
                lagou.setWorkYear(workYear.get(i));
                lagou.setCompanyFullName(companyFullName.get(i));
                lagou.setDegree(degree.get(i));
                lagou.setFinanceStage(financeStage.get(i));
                lagou.setIndustryField(industryField.get(i));
                lagou.setPositionAdvantage(positionAdvantage.get(i));
                lagou.setCity(city.get(i));
                lagou.setDistrict(district.get(i));
                lagou.setCompanySize(companySize.get(i));
                this.save(lagou);
            }
        }
        startFlag = true;
    }
}
