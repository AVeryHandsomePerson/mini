package com.cn.web.ui.mini.controller;

import com.alibaba.fastjson.JSON;
import com.cn.web.ui.mini.util.MyGetJd;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author dell
 * @Auther: ljh
 * @Date: 2019/8/6 15:16
 * @Description:
 */
@Controller
@Slf4j
public class EgController {

    private static Logger logger = LoggerFactory.getLogger(EgController.class);
    @Autowired
    private MyGetJd myGetJd;

    @RequestMapping("/")
    public ModelAndView getIndex() {
        ModelAndView modelAndView = new ModelAndView("/index");
        return modelAndView;
    }


    @RequestMapping("/jd")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView("/jingdong");
        modelAndView.addObject("name","京东前10");
        modelAndView.addObject("pillars", JSON.toJSONString(myGetJd.getGardenJD1()));
        return modelAndView;
    }

    @RequestMapping("/zhihu")
    public ModelAndView getZhuHuData() {
        ModelAndView modelAndView = new ModelAndView("/zhihu");
        modelAndView.addObject("name","知乎");
//        modelAndView.addObject("result", JSON.toJSONString(my.getGardenJD()));
//        modelAndView.addObject("pillars", JSON.toJSONString(myGetJd.getGardenJD1()));
        modelAndView.addObject("pillars", myGetJd.getGardenZhiHu());
        return modelAndView;
    }

    @RequestMapping("/douban")
    public ModelAndView getDouBanData() {
        ModelAndView modelAndView = new ModelAndView("/douban");
        modelAndView.addObject("name","豆瓣小组");
        modelAndView.addObject("pillars", myGetJd.getGardenDouBan());
        return modelAndView;
    }

    @RequestMapping("/theory")
    public ModelAndView getDouBanTheoryData() {
        ModelAndView modelAndView = new ModelAndView("/doubantheory");
        modelAndView.addObject("name","豆瓣小组");
        modelAndView.addObject("pillars", myGetJd.getGardenDouThearyBan());
        return modelAndView;
    }



    @RequestMapping("/weixin")
    public ModelAndView getWeiXinData() {
        ModelAndView modelAndView = new ModelAndView("/weixin");
        modelAndView.addObject("name","微信公众号");
        modelAndView.addObject("pillars", myGetJd.getWeiXinBan());
        return modelAndView;
    }

    @RequestMapping("download")
    public void download(HttpServletRequest servlet, HttpServletResponse response){
        logger.info("开始下载");
        List<Map<String,Object>> result = myGetJd.getGardenZhiHu();
        String fileName = "test.csv";
        //获取系统目录
//        String filePath = servlet.getSession().getServletContext().getRealPath("");
        String filePath = "D:\\excel\\";
        StringBuilder stringBuilder = new StringBuilder().append(filePath).append(fileName);
        //首先创建Wordbook
        OutputStream stream = null;
        InputStream inputStream = null;
        try {
            File file = new File(stringBuilder.toString());
            if(!file.exists()){

                file.createNewFile();
            }
            HSSFWorkbook workbook = new HSSFWorkbook();
            //生成工作簿
            HSSFSheet sheet = workbook.createSheet("ceshi");
            //生成表格
            HSSFRow row = sheet.createRow(0);
//            row.createCell(0).setCellValue("名称");
//            row.createCell(1).setCellValue("数量");
//            row.createCell(2).setCellValue("图片");
//            row.createCell(3).setCellValue("路径");
//            row.createCell(4).setCellValue("ip地址");
            for(int i=1 ;i<= result.size() ;i++ ){
                row = sheet.createRow(i);
                if(!CollectionUtils.isEmpty(result.get(i-1))){
                    row.createCell(0).setCellValue(result.get(i-1).getOrDefault("name","").toString());
                    row.createCell(1).setCellValue(result.get(i-1).getOrDefault("value","").toString());
                    row.createCell(2).setCellValue(result.get(i-1).getOrDefault("value","").toString());
                    row.createCell(3).setCellValue(result.get(i-1).getOrDefault("value","").toString());
                    row.createCell(4).setCellValue(result.get(i-1).getOrDefault("value","").toString());
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            workbook.close();

            //设置下载参数
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition","attachment;filename=text.csv");
            stream = response.getOutputStream();

            inputStream = new FileInputStream(file);

            //循环流
            int b = 0;
            while((b = inputStream.read()) != -1 ){
                stream.write(b);
            }
            //关闭资源
            stream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(!Objects.isNull(stream)){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!Objects.isNull(inputStream)){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //条状页面 就可以了
    @RequestMapping("homePage")
    public ModelAndView homePage(){
        ModelAndView view = new ModelAndView("/jd");
        return view;
    }

    //条状页面 就可以了
    @RequestMapping("ceshi")
    public ModelAndView homeCheShi(){
        ModelAndView view = new ModelAndView("ceshi/ceshi");
        return view;
    }


}