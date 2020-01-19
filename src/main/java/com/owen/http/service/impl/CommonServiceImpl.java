package com.owen.http.service.impl;

import com.owen.http.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public String downLoad(String url, HttpServletResponse response, HttpServletRequest request) {
        InputStream inputStream =null;
        try {
            URL downUrl = new URL(url);
            URLConnection connection = downUrl.openConnection();
//            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
//            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; CIBA)");
            inputStream = connection.getInputStream();
             int total = connection.getContentLength();
            ServletOutputStream outputStream = response.getOutputStream();
            SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
            String filename = format.format(new Date())+".mp4";
            //要下载的这个文件的类型----客户端通过文件的MIME类型去区分类型
//            response.setContentType("video/mpeg4; charset=utf-8");
            response.setContentType("video/mpeg4");
            //告诉客户端该文件不是直接解析 而是以附件的形式打开（下载）
            response.setHeader("Content-Disposition","attachment;filename=" + filename);
            int length;
            byte[] bytes = new byte[1024];
            while ((length = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
                outputStream.flush();
            }

//            byte[] bytes = new byte[total];
//            int read;
//            try {
//                 read= inputStream.read(bytes);
//            }catch (IOException e){
//                log.error("源文件IO异常："+e.getMessage());
//            }
//            outputStream.write(bytes);
//            outputStream.flush();
        }catch (MalformedURLException e){
            log.error("url错误:"+e.getMessage());
            return "url错误";
        }catch (IOException e){
            log.error("IO异常："+e.getMessage());
            return "IO异常";
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                }catch (Exception e){
                    log.error("IO关闭异常："+e.getMessage());
                }

            }
        }
        return "success";
    }

}
