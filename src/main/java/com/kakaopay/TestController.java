package com.kakaopay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @RequestMapping("/")
    public Map index(){
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");
        return map;
    }
}
