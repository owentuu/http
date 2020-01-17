package com.owen.http.controller;

import com.owen.http.service.CommonService;
import com.owen.http.service.XiGuaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping("/common")
@RestController
public class CommonController {

    @Autowired
    private CommonService commonService;

    @GetMapping("/download")
    public String converseText(@RequestParam("url") String url, HttpServletResponse response, HttpServletRequest request){
        try {
            return commonService.downLoad(url,response,request);
        }catch (Exception e){
            log.error("解析失败，参数==>"+url+",错误原因："+e.getMessage());
            return null;
        }
    }
}
