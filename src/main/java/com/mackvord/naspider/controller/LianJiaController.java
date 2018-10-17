package com.mackvord.naspider.controller;

import com.mackvord.naspider.processor.LianJiaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: mackvord@gmail.com
 * @date: 18-10-15
 * @time: 下午9:22
 */
@Controller
public class LianJiaController {
    @Autowired
    private LianJiaProcessor lianJiaProcessor;

    @RequestMapping("/lianjia")
    public String start() {
        lianJiaProcessor.start(lianJiaProcessor);
        return "LianJiaSpider is close!";
    }
}
