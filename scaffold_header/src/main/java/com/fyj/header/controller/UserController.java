package com.fyj.header.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fyj.Entity.User;
import com.fyj.common.entity.Result;
import com.fyj.common.entity.ResultCode;
import com.fyj.common.utils.IdWorkers;
import com.fyj.header.service.UserPwdService;
import com.fyj.header.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPwdService userPwdService;

    @Autowired
    private IdWorkers idWorkers;

    @GetMapping(value = "/all")
    public Result findAll() {
        List<User> list = userService.findAll();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(list);
        return new Result(ResultCode.SUCCESS, result);
    }

    @DeleteMapping(value = "/remove/{userId}")
    public Result deleteUser(@PathVariable("userId") String userId){
        userService.deleteByUserId(userId);
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping(value = "/login")
    public Result login(@RequestBody Map<String, String> map) {
        try {
            String s = new Md5Hash(map.get("password"), map.get("userId"), 3).toString();
            if (map.get("userId").equals(userPwdService.userIdGet((map.get("userId"))))
                    && s.equals(userPwdService.getPwdById(map.get("userId")))) {
                return new Result(ResultCode.SUCCESS);
            } else {
                return new Result(ResultCode.FAIL);
            }
        } catch (Exception e) {
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }
    }


}
