package com.mackvord.naspider.dao;

import com.mackvord.naspider.model.LianJia;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: mackvord@gmail.com
 * @date: 18-10-16
 * @time: 下午8:30
 */
@Mapper
public interface LianJiaMapper {

    /**
     * 保存数据到数据库
     * @param lianJia
     */
    void save(LianJia lianJia);

}
