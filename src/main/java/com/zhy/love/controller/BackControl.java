package com.zhy.love.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: zhangocean
 * @Date: 2018/10/22 15:47
 * Describe:
 */
@Controller
public class BackControl {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/yesterday")
    public String yesterday(){
        return "yesterday";
    }

    @GetMapping("/today")
    public String today(){
        return "today";
    }

}
