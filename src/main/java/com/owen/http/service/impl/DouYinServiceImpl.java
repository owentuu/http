package com.owen.http.service.impl;

import com.owen.http.service.DouYinService;
import com.owen.http.util.DelogoUtil;
import com.owen.http.util.HtmlUtilApache;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;

import static org.apache.http.impl.client.HttpClients.*;

@Slf4j
@Service
public class DouYinServiceImpl implements DouYinService {
    /**
     * 获取url源码，处理后返回
     *
     * @param inputText
     * @return
     */
    @Override
    public String converseInputText(String inputText) {
        String url = DelogoUtil.getShareUrl(inputText);
        return DelogoUtil.getDouyinDelogoUrl(HtmlUtilApache.getHtml(url));
    }

}
