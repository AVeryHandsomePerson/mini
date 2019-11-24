package com.cn.web.ui.mini.util;

import com.alibaba.fastjson.JSON;
import freemarker.ext.beans.HashAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Repository
public class MyGetJd {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getGardenJD1() {
        String sql = "SELECT book_name,witer_name,url,product_count,dt FROM jd order by product_count desc limit 10";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        List<Map<String, Object>> data = result.stream().map(map -> {
            Map<String, Object> m = new HashMap<>(2);
            m.put("value", map.getOrDefault("product_count", 0));
            m.put("name", map.getOrDefault("witer_name", "") + "-" + map.getOrDefault("book_name", ""));
            return m;
        }).collect(Collectors.toList());
        return data;
    }

    public List<Map<String, Object>> getGardenZhiHu() {
        String sql = "SELECT anwer_name,anwer_number FROM zhihu";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        List<Map<String, Object>> data = result.stream().map(map -> {
            Map<String, Object> m = new HashMap<>(2);
            m.put("name", map.getOrDefault("anwer_name", 0));
            m.put("value", map.getOrDefault("anwer_number", ""));
            return m;
        }).collect(Collectors.toList());
        return data;
    }


    public List<Map<String, Object>> getGardenDouBan() {
        String sql = "SELECT grouping_name,grouping_personnel FROM db_grouping";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        List<Map<String, Object>> data = result.stream().map(map -> {
            Map<String, Object> m = new HashMap<>(2);
            m.put("name", map.getOrDefault("grouping_name", 0));
            m.put("value", map.getOrDefault("grouping_personnel", ""));
            return m;
        }).collect(Collectors.toList());
        return data;
    }

    public List<Map<String, Object>> getGardenDouThearyBan() {
        String sql = "SELECT theory_namev,theory_number FROM douban_theory";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        List<Map<String, Object>> data = result.stream().map(map -> {
            Map<String, Object> m = new HashMap<>(2);
            m.put("name", map.getOrDefault("theory_namev", 0));
            m.put("value", map.getOrDefault("theory_number", ""));
            return m;
        }).collect(Collectors.toList());
        return data;
    }


    public List<Map<String, Object>> getWeiXinBan() {
        String sql = "SELECT gz_name,gz_number FROM wx";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        List<Map<String, Object>> data = result.stream().map(map -> {
            Map<String, Object> m = new HashMap<>(2);
            m.put("name", map.getOrDefault("gz_name", 0));
            m.put("value", map.getOrDefault("gz_number", ""));
            return m;
        }).collect(Collectors.toList());
        return data;
    }
/*    public static void main(String[] args) {
//        JSONObject object = new JSONObject();
//        object.put("value",300);
//        object.put("name","dasd");
        System.out.println(new MyGetJd().getGardenJD1());
    }*/

}

