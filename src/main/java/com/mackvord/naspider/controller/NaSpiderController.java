package com.mackvord.naspider.controller;

import com.mackvord.naspider.processer.NaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: mackvord@gmail.com
 * @date: 18-10-3
 * @time: 下午12:28
 */
@Controller
public class NaSpiderController {
    @Autowired
    private NaProcessor naProcessor;

    @RequestMapping("/start")
    public String start() {
        naProcessor.start(naProcessor);
        return "NaSpider is close!";
    }
}
