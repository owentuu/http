package com.owen.http.service.impl;

import com.owen.http.service.KuaiShouService;
import com.owen.http.util.HtmlUtilApache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class KuaiShouServiceImpl implements KuaiShouService {
    /**
     * 获取url源码，处理后返回
     *
     * @param inputText
     * @return
     */
    @Override
    public String converseInputText(String inputText) {
        String shareUrl = getShareUrl(inputText);
        System.out.println(HtmlUtilApache.getHtml(shareUrl));
        return null;
    }

    private String getShareUrl(String inputText) {
        if (!StringUtils.isBlank(inputText)) {
            //使用正则表达式匹配出来playAddr
            Pattern pattern = Pattern.compile("(http).*");
            Matcher matcher = pattern.matcher(inputText);
            while (matcher.find()) {
                String group = matcher.group();
                return group;
            }
        }
        return null;
    }
}
