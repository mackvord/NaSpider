package com.mackvord.naspider.dao;

import com.mackvord.naspider.model.Video;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: mackvord@gmail.com
 * @date: 18-10-3
 * @time: 上午10:36
 */
@Mapper
public interface VideoDao {

    /**
     * 保存Video数据到数据库
     * @param video Video对象
     */
    void save(Video video);

}
