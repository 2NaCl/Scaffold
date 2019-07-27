package com.fyj.header.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fyj.Entity.User;
import com.fyj.common.entity.Result;
import com.fyj.common.entity.ResultCode;
import com.fyj.common.utils.IdWorkers;
import com.fyj.header.service.UserPwdService;
import com.fyj.header.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
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

//    @PostMapping(value = "/login")
//    public Result login(@RequestBody Map<String, String> map) {
//        try {
//            String s = new Md5Hash(map.get("password"), map.get("userId"), 3).toString();
//            if (map.get("userId").equals(userPwdService.userIdGet((map.get("userId"))))
//                    && s.equals(userPwdService.getPwdById(map.get("userId")))) {
//                return new Result(ResultCode.SUCCESS);
//            } else {
//                return new Result(ResultCode.FAIL);
//            }
//        } catch (Exception e) {
//            return new Result(ResultCode.MOBILEORPASSWORDERROR);
//        }
//    }

    /**
     * Authentication：身份认证/登录，验证用户是不是拥有响应的身份。
     * Authorization：授权，验证某个已认证的用户是否拥有某个权限，判断用户是否能做事情。
     * Session Management：会话管理，用户登录后就是一次会话，在没有退出之前，所有信息都会在会话中，会话可以是SE也可以是EE
     * Cryptography：数据加密
     * Web Support：支持Web开发
     * Caching：缓存，比如用户登录之后，不需要再次查阅自己的角色和权限
     * Concurrency：支持并发
     * Testing：支持测试，保证安全
     * “Run As”：管理脚本
     * Remember Me：记住我
     * @param map
     * @return
     */
    //shiro登录
    @PostMapping(value = "/login")
    public Result login(@RequestBody Map<String, String> map) {
        String userId = map.get("userId");
        String password = new Md5Hash(map.get("password"), userId, 3).toString();
        try {
            //构造登录令牌
            UsernamePasswordToken upToken = new UsernamePasswordToken(userId, password);
            //1.获取subject
            Subject subject = SecurityUtils.getSubject();
            //2.调用subject进行登录
            subject.login(upToken);
            //3.获取sessionId
            String sessionId = (String) subject.getSession().getId();
            //4.构造返回结果
            return new Result(ResultCode.SUCCESS, sessionId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }
    }


}
