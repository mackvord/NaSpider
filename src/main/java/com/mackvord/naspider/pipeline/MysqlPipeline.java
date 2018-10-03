package com.mackvord.naspider.pipeline;

import com.mackvord.naspider.dao.VideoDao;
import com.mackvord.naspider.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: mackvord@gmail.com
 * @date: 18-10-3
 * @time: 上午10:57
 */
@Component
public class MysqlPipeline {
    @Autowired
    private VideoDao videoDao;

    public void save(Video video) {
        videoDao.save(video);
    }
}
