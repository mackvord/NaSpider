package com.mackvord.naspider.dao;

import com.mackvord.naspider.model.Lagou;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: mackvord@gmail.com
 * @date: 18-10-2
 * @time: 下午1:36
 */
@Mapper
public interface LagouMapper {

    /**
     * 保存数据库到数据库
     * @param lagou Lagou对象
     */
    void save(Lagou lagou);

}
