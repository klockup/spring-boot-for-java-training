package com.train.all.java.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  简单的后端访问接口
 * @author: kennys.chai
 * @date: 2022/1/3
 */
@RestController
@Slf4j
@RequestMapping("/web")
public class WebController {

    @GetMapping("/index")
    public String index(){
        log.info("2222");
        return "success";
    }


}
