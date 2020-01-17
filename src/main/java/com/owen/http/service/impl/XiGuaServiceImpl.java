package com.owen.http.service.impl;

import com.owen.http.service.XiGuaService;
import com.owen.http.util.HtmlUtilApache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class XiGuaServiceImpl implements XiGuaService {
    /**
     * 获取url源码，处理后返回
     *
     * @param inputText
     * @return
     */
    @Override
    public String converseInputText(String inputText) {
        String url = getShareUrl(inputText);
        String html = HtmlUtilApache.getHtml(url);
        System.out.println(html);
        String converseUrl = getConverseUrl(html);
        System.out.println(converseUrl);
        return converseUrl;
    }

    private String getShareUrl(String inputText){
        if (!StringUtils.isBlank(inputText)) {
            //使用正则表达式匹配出来playAddr
            Pattern pattern = Pattern.compile("(http).*");
            Matcher matcher = pattern.matcher(inputText);
            while (matcher.find()) {
                String group = matcher.group();
                String html = HtmlUtilApache.getHtml(group);
                Pattern pattern1 = Pattern.compile("(https).*\\?");
                Matcher matcher1 = pattern1.matcher(html);
                while (matcher1.find()){
                    String group1 = matcher1.group();
                    String i = group1.replace("group/", "i");
                    String replace = i.replace("?", "");
                    return replace;
                }
//                return group;
            }
        }
        return null;
    }

    private String getConverseUrl(String html){
        if (!StringUtils.isBlank(html)) {
            System.out.println(html);
            //使用正则表达式匹配出来playAddr
            Pattern pattern = Pattern.compile("(v1-tt)");
            Matcher matcher = pattern.matcher(html);
            while (matcher.find()) {
                String group = matcher.group();
//                LogUtils.e("group\n" + group);
                log.error("group\n" + group);
//                String replace = group.replace("playwm", "play");
                return "http:"+group;
            }
        }
        return null;
    }
}
