package com.cn.web.ui.mini.controller;

import com.alibaba.fastjson.JSON;
import com.cn.web.ui.mini.util.MyGetJd;
import com.cn.web.ui.mini.util.MysqlUtile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dell
 * @Auther: ljh
 * @Date: 2019/8/6 15:16
 * @Description:
 */
@Controller
@Slf4j
public class EgController {
    @Autowired
    private MyGetJd myGetJd;
    @RequestMapping("/")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView("/ceshi");
        modelAndView.addObject("name","京东前10");
//        modelAndView.addObject("result", JSON.toJSONString(my.getGardenJD()));
//        modelAndView.addObject("pillars", JSON.toJSONString(myGetJd.getGardenJD1()));
        modelAndView.addObject("pillars", JSON.toJSONString(myGetJd.getGardenJD1()));
        return modelAndView;
    }
}