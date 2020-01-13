package com.owen.http.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DelogoUtil {

    /**
     * 从html中获取抖音的无水印连接
     *
     * @param html
     * @return
     */
    public static String getDouyinDelogoUrl(String html) {
        if (!StringUtils.isBlank(html)) {
            //使用正则表达式匹配出来playAddr
            Pattern pattern = Pattern.compile("(?<=playAddr:..).*(?=.,)");
            Matcher matcher = pattern.matcher(html);
            while (matcher.find()) {
                String group = matcher.group();
//                LogUtils.e("group\n" + group);
                log.error("group\n" + group);
                String replace = group.replace("playwm", "play");
                return replace;
            }
        }
        return null;
    }

    /**
     * 将用户输入的内容里，获取到抖音的shareUrl
     * <p>
     * 例如：
     * #在抖音，记录美好生活#这个视频，答应臭妹妹看完好吗？#臭妹妹 #颜值 #正能量 http://v.douyin.com/HFysdH/ 复制此链接，打开【抖音短视频】，直接观看视频！
     * <p>
     * 从http开始，到最后一个/结束的内容
     */
    public static String getShareUrl(String inputText) {
        if (!StringUtils.isBlank(inputText)) {
            //使用正则表达式匹配出来playAddr
            Pattern pattern = Pattern.compile("(http).*/");
            Matcher matcher = pattern.matcher(inputText);
            while (matcher.find()) {
                String group = matcher.group();
                return group;
            }
        }
        return null;
    }
}