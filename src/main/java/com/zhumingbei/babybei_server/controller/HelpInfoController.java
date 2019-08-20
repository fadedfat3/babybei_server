package com.zhumingbei.babybei_server.controller;

import com.zhumingbei.babybei_server.bean.HelpInfo;
import com.zhumingbei.babybei_server.config.HelpInfoConfig;
import com.zhumingbei.babybei_server.constant.StatusCodeConstant;
import com.zhumingbei.babybei_server.exception.BaseException;
import com.zhumingbei.babybei_server.service.HelpInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class HelpInfoController {
    @Autowired
    private HelpInfoService helpInfoService;
    @Autowired
    private HelpInfoConfig helpInfoConfig;

    @GetMapping(value = "helpInfo/{type}")
    public List<HelpInfo> getInfo(@PathVariable("type") Integer type, @RequestParam(value = "page",required = false) Integer page) {
        if(page == null){
            page = 0;
        }
        log.debug("test loging");
        log.info("test page = {}", page);
        log.error("test type = {}", type);
        int pageSize = helpInfoConfig.getPageSize();
        return helpInfoService.getInfo(type, pageSize, page * pageSize);
    }

    @GetMapping("/helpInfo")
    public List<HelpInfo> getAllList() {
        throw new BaseException(StatusCodeConstant.UNKNOWN_ERROR);
    }


}
