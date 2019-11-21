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

    public List<Map<String,Object>> getGardenJD1(){
       String sql = "SELECT book_name,witer_name,url,product_count,dt FROM jd order by product_count desc limit 10";

       List<Map<String,Object>> result = jdbcTemplate.queryForList(sql);
        List<Map<String,Object>>data = result.stream().map(map -> {
            Map<String,Object> m = new HashMap<>(2);
            m.put("value",map.getOrDefault("product_count",0));
            m.put("name",map.getOrDefault("witer_name","")+"-"+map.getOrDefault("book_name",""));
            return m;
        }).collect(Collectors.toList());
       return data;

    }

    private final String[] a = new String[]{"#2dc6c8", "#b6a2dd", "#5ab1ee", "#d7797f", "#d7797f", "#EA7500", "#6C3365", "#B766AD", "#C07AB8", "#B87070"};
    public ArrayList<ArrayList> getGardenJD() {
        ArrayList<ArrayList> lsit = new ArrayList();
        try {
            Statement stmt = null;
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = MysqlUtile.getConnectionJD().createStatement();
            String sql;
            sql = "SELECT book_name,witer_name,url,product_count,dt FROM jd order by product_count desc limit 10";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            int i = 0;
            while (rs.next()) {
                // 通过字段检索
                String name = rs.getString("witer_name");
                String book_name = rs.getString("book_name");
                String product_count = rs.getString("product_count");
                ArrayList<Object> arr = new ArrayList();
                arr.add(Integer.valueOf(Pattern.compile("[^0-9]").matcher(product_count).replaceAll("")));
//                arr.add(a[i]);
                arr.add(book_name+":"+name);
                lsit.add(arr);
                i++;
            }
            // 完成后关闭
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        }
        return lsit;
    }
    public ArrayList<ArrayList> getPillarsJD() {
        ArrayList<ArrayList> lsit = new ArrayList();
        try {
            Statement stmt = null;
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = MysqlUtile.getConnectionJD().createStatement();
            String sql;
            sql = "SELECT book_name,witer_name,url,product_count,dt FROM jd order by product_count desc limit 10";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                String name = rs.getString("witer_name");
                String book_name = rs.getString("book_name");
                String product_count = rs.getString("product_count");
                ArrayList<Object> arr = new ArrayList();

                arr.add(book_name+":"+name);
                arr.add(Integer.valueOf(Pattern.compile("[^0-9]").matcher(product_count).replaceAll("")));
                lsit.add(arr);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        }
        return lsit;
    }

/*    public static void main(String[] args) {
//        JSONObject object = new JSONObject();
//        object.put("value",300);
//        object.put("name","dasd");
        System.out.println(new MyGetJd().getGardenJD1());
    }*/

}

