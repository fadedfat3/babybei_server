package com.zhumingbei.babybei_server.service;

import com.zhumingbei.babybei_server.bean.HelpInfo;
import com.zhumingbei.babybei_server.mapper.HelpInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpInfoService {
    @Autowired
    private HelpInfoMapper helpInfoMapper;

    public List<HelpInfo> getInfo(int type, int limit, int offset) {
        return helpInfoMapper.findByType(type, limit, offset);
    }
}
