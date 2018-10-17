package com.mackvord.naspider.pipeline;

import com.mackvord.naspider.dao.VideoMapper;
import com.mackvord.naspider.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: mackvord@gmail.com
 * @date: 18-10-3
 * @time: 上午10:57
 */
@Component
public class NaMysqlPipeline {
    @Autowired
    private VideoMapper videoMapper;

    public void save(Video video) {
        videoMapper.save(video);
    }

}
