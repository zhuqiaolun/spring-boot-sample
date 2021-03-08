package com.demon.sample.controller;

import com.alibaba.fastjson.JSONObject;
import com.demon.sample.config.yml.UserConfig;
import com.demon.sample.support.BaseController;
import com.demon.sample.support.ResponseBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Demon
 * @date 2021/3/5 14:56
 * @description: 测试
 */
@RestController
@RequestMapping(value = "api/test")
public class TestController extends BaseController {

    @Resource
    private UserConfig userConfig;

    @GetMapping
    public @ResponseBody
    void get(@RequestParam(name = "username", required = false) String username, HttpServletResponse response) {
        ResponseBean responseBean = new ResponseBean();
        try {
            getUser(responseBean, username);
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setSuccess(false);
            responseBean.setErrCode("00001");
            responseBean.setErrMsg("获取用户信息异常");
        }
        this.out(response, responseBean);
    }

    @PostMapping
    public @ResponseBody
    void post(@RequestBody JSONObject jsonParam, HttpServletResponse response) {
        ResponseBean responseBean = new ResponseBean().setDataFormat(false);
        try {
            String[] params = {"username"};
            if (this.getParams(responseBean, jsonParam, params)) {
                String username = jsonParam.getString("username");
                getUser(responseBean, username);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setSuccess(false);
            responseBean.setErrCode("00001");
            responseBean.setErrMsg("获取用户信息异常");
        }
        this.out(response, responseBean);
    }

    private void getUser(ResponseBean responseBean, String username) {

        String password = userConfig.getUser().get(username);
        if (StringUtils.isNotBlank(password)) {
            List<Map<String, Object>> mapList = new ArrayList<>();
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("username", username);
            map.put("password", password);
            mapList.add(map);
            responseBean.setData(mapList).setSuccess(true);
        } else {
            responseBean.setSuccess(false);
            responseBean.setErrCode("20001");
            responseBean.setErrMsg("【" + username + "】用户不存在");
        }
    }

}
