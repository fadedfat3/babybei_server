package com.zhumingbei.babybei_server.service;

import com.zhumingbei.babybei_server.common.MatchCode;
import com.zhumingbei.babybei_server.entity.User;

/**
 * @author fadedfate
 * @date Created at 2019/9/5 14:54
 */
public interface MatchCodeService {
    MatchCode refresh(int userID);

    MatchCode match(User user, Long code);
}
