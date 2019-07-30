package com.zhumingbei.babybei_server.mapper;

import com.zhumingbei.babybei_server.bean.HelpInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpInfoMapper {
    List<HelpInfo> findByType(int type, int limit, int offset);
}
