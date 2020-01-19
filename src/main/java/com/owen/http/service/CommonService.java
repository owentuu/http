package com.owen.http.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommonService {

    String downLoad(String url,HttpServletResponse response, HttpServletRequest request);

}
