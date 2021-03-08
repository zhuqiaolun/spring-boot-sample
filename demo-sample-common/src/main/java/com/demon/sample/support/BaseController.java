package com.demon.sample.support;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author: Demon
 * @date 2021/3/5 14:24
 * @description: 基本 Controller
 */
@Slf4j
public abstract class BaseController {

    /**
     * 使用response输出JSON
     *
     * @param response response
     * @param str      响应对应
     */
    protected void out(HttpServletResponse response, Object str) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.print(str);
        } catch (Exception e) {
            log.error("输出JSON出错", e.getMessage());
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 判断参数 是否存在
     */
    protected boolean getParams(ResponseBean responseBean, JSONObject jsonParam, String[] params) {
        boolean flag = false;
        for (String param : params) {
            if (jsonParam.containsKey(param)) {
                if (StringUtils.isNotBlank(String.valueOf(jsonParam.get(param)))) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            } else {
                flag = false;
                break;
            }
        }
        if (!flag) {
            responseBean.setSuccess(false);
            responseBean.setErrMsg("缺少参数");
            responseBean.setErrCode("40003");
        }
        return flag;
    }

}
