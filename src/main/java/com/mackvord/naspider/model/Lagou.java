package com.mackvord.naspider.model;

/**
 * @author: mackvord@gmail.com
 * @date: 18-10-5
 * @time: 下午4:24
 */
public class Lagou {

    /**
     * 主键id,自增
     */
    private Integer id;

    /**
     * 职位
     */
    private String positionName;

    /**
     * 薪资
     */
    private String salary;

    /**
     * 工作年限
     */
    private String workYear;

    /**
     * 公司全名
     */
    private String companyFullName;

    /**
     * 公司所在城市
     */
    private String city;

    /**
     * 学位
     */
    private String degree;

    /**
     * 区域
     */
    private String district;

    /**
     * 融资情况
     */
    private String financeStage;

    /**
     * 职位优势
     */
    private String positionAdvantage;

    /**
     * 行业领域
     */
    private String industryField;

    /**
     * 公司规模
     */
    private String companySize;

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFinanceStage() {
        return financeStage;
    }

    public void setFinanceStage(String financeStage) {
        this.financeStage = financeStage;
    }

    public String getPositionAdvantage() {
        return positionAdvantage;
    }

    public void setPositionAdvantage(String positionAdvantage) {
        this.positionAdvantage = positionAdvantage;
    }

    public String getIndustryField() {
        return industryField;
    }

    public void setIndustryField(String industryField) {
        this.industryField = industryField;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    @Override
    public String toString() {
        return "Lagou{" +
                "id=" + id +
                ", positionName='" + positionName + '\'' +
                ", salary='" + salary + '\'' +
                ", workYear='" + workYear + '\'' +
                ", companyFullName='" + companyFullName + '\'' +
                ", city='" + city + '\'' +
                ", degree='" + degree + '\'' +
                ", district='" + district + '\'' +
                ", financeStage='" + financeStage + '\'' +
                ", positionAdvantage='" + positionAdvantage + '\'' +
                ", industryField='" + industryField + '\'' +
                ", companySize='" + companySize + '\'' +
                '}';
    }
}
