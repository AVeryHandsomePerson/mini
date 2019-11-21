package com.cn.web.ui.mini;

import com.alibaba.fastjson.JSON;
import com.cn.web.ui.mini.util.MyGetJd;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class JdbcTest extends MiniApplicationTests{

    @Autowired
    private MyGetJd myGetJd;

    @Test
    public void jdbcTestm(){
        List<Map<String,Object>> t = myGetJd.getGardenJD1();
        t.stream().forEach(map -> {
            System.out.println(JSON.toJSONString(map));
        });
    }
}
