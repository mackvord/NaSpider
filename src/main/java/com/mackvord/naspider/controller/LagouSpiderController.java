package com.mackvord.naspider.controller;

import com.mackvord.naspider.processor.LagouProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: mackvord@gmail.com
 * @date: 18-10-11
 * @time: 上午9:01
 */
@Controller
public class LagouSpiderController {

    @Autowired
    private LagouProcessor lagouProcessor;

    @RequestMapping("/lagou")
    public String start() {
        lagouProcessor.start(lagouProcessor);
        return "lagouSpider is close!";
    }
}
