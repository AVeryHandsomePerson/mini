package com.cn.web.ui.mini.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
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
    @RequestMapping("/")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView("/image");
        ArrayList<Object> arr = new ArrayList();
        arr.add(50);
        arr.add("#2dc6c8");
        arr.add("瓜子");
        List<ArrayList> result = new ArrayList<>();
        result.add(arr);
        ArrayList<Object> arr1 = new ArrayList();
        arr1.add(500);
        arr1.add("#2dc6c8");
        arr1.add("瓜子1");
        result.add(arr1);
//[50,"#2dc6c8","瓜子"], [100,"#b6a2dd", "花生"], [200,"#5ab1ee","土豆"], [700,"#d7797f","南瓜四号"], [70,"#d7797f","四号"]
        System.out.println(JSON.toJSONString(result));
        modelAndView.addObject("result", JSON.toJSONString(result));
        return modelAndView;
    }
}