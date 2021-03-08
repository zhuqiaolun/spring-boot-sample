package com.demon.sample.support;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.demon.sample.exception.EmptyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author: Demon
 * @date 2021/3/8 14:05
 * @description: 控制层工具类
 */
@Slf4j
public class ControllerTools extends BaseController {

    /**
     * 判断是否 ajax 请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    /**
     * ajax 输出
     */
    public static void print(HttpServletResponse response, String msg) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(msg);
        writer.flush();
        writer.close();
    }

    /**
     * 获取 requestBody 参数
     */
    public static JSONObject getJsonParam(HttpServletRequest request) throws JSONException {
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
            // 写入数据到StringBuilder
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            return JSONObject.parseObject(sb.toString());
        } catch (Exception e) {
            throw new JSONException("错误参数", e);
        }
    }

    /**
     * 验证参数为空
     */
    public static <T> T verifyNodeIsEmpty(ResponseBean responseBean, Object obj, Class<T> cls) {
        if (obj != null) {
            if (StringUtils.isBlank(String.valueOf(obj))) {
                responseBean.setSuccess(false);
                responseBean.setErrMsg("参数不能为空");
                responseBean.setErrCode("40004");
                throw new EmptyException(responseBean.getErrCode(), responseBean.getErrMsg());
            } else {
                return cls.cast(obj);
            }
        } else {
            responseBean.setSuccess(false);
            responseBean.setErrMsg("参数不能为空");
            responseBean.setErrCode("40004");
            throw new EmptyException(responseBean.getErrCode(), responseBean.getErrMsg());
        }
    }
}

