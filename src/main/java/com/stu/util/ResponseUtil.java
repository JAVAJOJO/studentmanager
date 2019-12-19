package com.stu.util;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
public class ResponseUtil {
	 /**
     * 工具方法，返回JSON数据
     * @param data
     * @param response
     * @throws IOException
     */
    public static void returnJson(Object data, HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("utf-8");
        //该实体头的作用是 让服务器告诉  浏览器 它发送的数据属于什么文件类型。
        response.setContentType("application/json");
        //没有缓存，网页的缓存是由HTTP消息头中的"Cache-control"来控制的，常见的取值有no-cache
        response.setHeader("cache-control", "no-cache");
        response.getWriter().write(JSON.toJSONString(data));
    }
}