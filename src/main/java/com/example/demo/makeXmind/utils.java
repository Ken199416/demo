package com.example.demo.makeXmind;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class utils {
    private static Logger logger = LoggerFactory.getLogger(utils.class);
    @RequestMapping("/js")
    public void test(){
        logger.info("收到js步码");
    }

}
