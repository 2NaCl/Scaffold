package com.fyj.header.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fyj.Entity.UserPwd;
import com.fyj.common.entity.Result;
import com.fyj.common.entity.ResultCode;
import com.fyj.common.utils.IdWorkers;
import com.fyj.header.service.UserPwdService;
import com.fyj.header.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/reg")
@RestController
public class UserPwdController {

    @Autowired
    private IdWorkers idWorkers;

    @Autowired
    private UserPwdService userPwdService;

    @Autowired
    private UserService userService;

    /**
     * 插入密码
     * @param map
     * @return
     */
    @PostMapping("/step1")
    public Result insertMsg(@RequestBody Map<String,String> map,
                            UserPwd userPwd) {
        userPwd.setUserId(map.get("userId"));
        userPwd.setPassword(new Md5Hash(map.get("password"),map.get("userId"),3).toString());
        userPwdService.insertMsg(map.get("userId"), new Md5Hash(map.get("password"), map.get("userId"), 3).toString());
        return new Result(ResultCode.SUCCESS,userPwd);
    }

}
