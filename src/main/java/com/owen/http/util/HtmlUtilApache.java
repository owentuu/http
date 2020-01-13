package com.owen.http.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@Slf4j
public class HtmlUtilApache {

    public static String getHtml(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 設置httpGet的头部參數信息 此处必不可少，伪装成浏览器访问
            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
            httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch, br");
            httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.8");
            httpGet.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            log.error("IO异常,"+e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                log.error("IO异常,"+e.getMessage());
            }
        }
        return resultString;
    }

    public static String getHtml(String url) {
        return getHtml(url, null);
    }
}
