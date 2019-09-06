package com.zhumingbei.babybei_server.controller;

import com.zhumingbei.babybei_server.common.ApiResponse;
import com.zhumingbei.babybei_server.common.MatchCode;
import com.zhumingbei.babybei_server.common.UserPrincipal;
import com.zhumingbei.babybei_server.entity.User;
import com.zhumingbei.babybei_server.service.MatchCodeService;
import com.zhumingbei.babybei_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fadedfate
 * @date Created at 2019/9/5 14:00
 */
@RestController
@RequestMapping("/match")
public class MatchController {
    @Autowired
    private MatchCodeService matchCodeService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/refresh")
    public MatchCode refresh() {
        User user = UserPrincipal.User();
        return matchCodeService.refresh(user.getId());
    }

    @PostMapping("/match")
    @ResponseBody
    public ApiResponse match(@RequestParam("code") Long code) {
        User user = UserPrincipal.User();
        if (user.getIsMatched() == 1) {
            return ApiResponse.of(200, "已经配对");
        }
        MatchCode matchCode = matchCodeService.match(user, code);

        if (matchCode != null) {
            user.setIsMatched(1);
            user.setPartnerId(matchCode.getUserID());
            userService.match(user);
            return ApiResponse.ofSuccess("配对成功");
        }
        return ApiResponse.of(4001, "配对失败");
    }

    @PostMapping("/dismatch")
    public ApiResponse dismatch() {
        User user = UserPrincipal.User();
        if (user.getIsMatched() == 0) {
            return ApiResponse.ofSuccess("已取消配对");
        }
        user.setIsMatched(0);
        userService.dismatch(user);
        return ApiResponse.ofSuccess("取消配对成功");
    }
}
