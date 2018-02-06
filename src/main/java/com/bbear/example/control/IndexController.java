package com.bbear.example.control;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author junxiongchen
 * @date 2017/11/23
 */
@Component
@RequestMapping("/home")
public class IndexController {
    @RequestMapping("/index")
    protected String index(){
        return "index";
    }
}
