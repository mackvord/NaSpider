package com.mackvord.naspider.model;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author: mackvord@gmail.com
 * @date: 18-10-16
 * @time: 下午8:20
 */
@Mapper
public class LianJia {
    /**
     * 描述
     */
    private String description;
    
    /**
     * 主键
     */
    private Integer id;
    
    /**
     * 租房价格
     */
    private double price;
     
    /**
     * 楼层
     */
    private String floor;
     
    /**
     * 地铁
     */
    private String subway;
     
    /**
     * 房屋户型
     */
    private String houseType;
     
    /**
     * 房屋朝向
     */
    private String direction;
     
    /**
     * 租房面积
     */
    private String area;
    
    /**
     * 租房位置
     */
    private String location;
    
    /**
     * 租房信息发布时间
     */
    private String releaseTime;
    
    /**
     * 居住区
     */
    private String residentialArea;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getSubway() {
        return subway;
    }

    public void setSubway(String subway) {
        this.subway = subway;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getResidentialArea() {
        return residentialArea;
    }

    public void setResidentialArea(String residentialArea) {
        this.residentialArea = residentialArea;
    }

    @Override
    public String toString() {
        return "LianJia{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", price=" + price +
                ", floor='" + floor + '\'' +
                ", subway='" + subway + '\'' +
                ", houseType='" + houseType + '\'' +
                ", direction='" + direction + '\'' +
                ", area='" + area + '\'' +
                ", location='" + location + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", residentialArea='" + residentialArea + '\'' +
                '}';
    }
}
