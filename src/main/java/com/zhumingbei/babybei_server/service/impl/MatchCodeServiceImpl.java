package com.zhumingbei.babybei_server.service.impl;

import com.zhumingbei.babybei_server.common.MatchCode;
import com.zhumingbei.babybei_server.entity.User;
import com.zhumingbei.babybei_server.service.MatchCodeService;
import com.zhumingbei.babybei_server.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fadedfate
 * @date Created at 2019/9/5 14:25
 */
@Slf4j
@Service
public class MatchCodeServiceImpl implements MatchCodeService {
    @Autowired
    private RedisUtil redisUtil;

    private MatchCode create(int userID) {
        Long createdTime = System.currentTimeMillis();
        Long code = createdTime >> 8;

        return new MatchCode(userID, code);
    }

    @Override
    public MatchCode refresh(int userID) {
        MatchCode code = create(userID);
        redisUtil.set("match_code." + userID, code, code.getTtl());
        return code;
    }

    @Override
    public MatchCode match(User user, Long code) {
        String key = "match_code.";
        String pattern = key + "*";
        for (String s : redisUtil.keys(pattern)) {
            MatchCode matchCode = (MatchCode) redisUtil.get(s);
            if (matchCode.getCode().equals(code)) {
                redisUtil.delete(s);
                redisUtil.delete(key + user.getId());
                return matchCode;
            }
        }
        return null;
    }
}
