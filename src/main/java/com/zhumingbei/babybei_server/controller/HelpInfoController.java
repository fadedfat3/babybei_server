package com.zhumingbei.babybei_server.controller;

import com.zhumingbei.babybei_server.bean.HelpInfo;
import com.zhumingbei.babybei_server.config.HelpInfoConfig;
import com.zhumingbei.babybei_server.service.HelpInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelpInfoController {
    @Autowired
    private HelpInfoService helpInfoService;
    @Autowired
    private HelpInfoConfig helpInfoConfig;
    private Logger logger = LoggerFactory.getLogger(HelpInfoController.class);
    @GetMapping(value = "helpInfo/{type}")
    public List<HelpInfo> getInfo(@PathVariable("type") Integer type, @RequestParam(value = "page",required = false) Integer page) {
        if(page == null){
            page = 0;
        }
        logger.debug("test loging");
        logger.info("test page = {}", page);
        logger.error("test type = {}", type);
        int pageSize = helpInfoConfig.getPageSize();
        return helpInfoService.getInfo(type, pageSize, page * pageSize);
    }

}
