package com.fyj.header.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fyj.Entity.User;
import com.fyj.Entity.UserPwd;
import com.fyj.common.entity.Result;
import com.fyj.common.entity.ResultCode;
import com.fyj.common.utils.IdWorkers;
import com.fyj.header.service.UserPwdService;
import com.fyj.header.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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
     * 插入个人信息
     */
    @PostMapping("/step2")
    public Result insert(@RequestBody Map<String, String> map,
                         User user) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            String userId = userService.findId(map.get("userId"));
            if (userId == null) {
                String id = idWorkers.nextId() + "";
                user.setBirthday(sdf.format(new Date().getTime()));
                user.setLoginDate(sdf.format(new Date().getTime()));
                user.setUserId(map.get("userId"));
                user.setCity(map.get("city"));
                user.setEmail(map.get("email"));
                user.setHome(map.get("home"));
                user.setIdCard(map.get("idCard"));
                user.setPhone(map.get("phone"));
                user.setLevel("user");
                user.setId(id);
                userService.saveAll(user);
                return new Result(ResultCode.SUCCESS, user);
            }else {
                return new Result(ResultCode.FAIL, null);
            }
        }catch (Exception e) {
            return new Result(ResultCode.ALREADLY);
        }
    }

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

    @PutMapping("/changePwd")
    public Result changePwd(@RequestBody Map<String,String> map) {
        try {
            if (userPwdService.userIdGet(map.get("userId")).equals(map.get("userId"))) {
                userPwdService.changePwd(map.get("userId"),map.get("password"));
                String pwd = userPwdService.passwordGet(map.get("userId"));
                String md5Pwd = new Md5Hash(pwd, map.get("userId"), 3).toString();
                return new Result(ResultCode.SUCCESS,md5Pwd);
            } else {
                return new Result(ResultCode.ALREADLY);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAIL);
        }


    }

}
