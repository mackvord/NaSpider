package com.mackvord.naspider.model;

/**
 * @author: mackvord@gmail.com
 * @date: 18-10-3
 * @time: 上午10:27
 */
public class Video {

    /**
     * 视频id，主键自增
     */
    private String id;

    /**
     * 视频名称
     */
    private String name;

    /**
     * 视频分类
     */
    private String category;

    /**
     * 视频封面
     */
    private String cover;

    /**
     * 视频链接
     */
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", cover='" + cover + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
