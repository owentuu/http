package com.owen.http.controller;

import com.owen.http.service.DouYinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DoYinController {

    @Autowired
    private DouYinService douYinService;

    @GetMapping("/converse")
    public String converseText(@RequestParam("inputText") String inputText){
        try {
            return douYinService.converseInputText(inputText);
        }catch (Exception e){
            log.error("解析失败，参数==>"+inputText+",错误原因："+e.getMessage());
            return null;
        }
    }
}
