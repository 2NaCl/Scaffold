package com.fyj.header.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fyj.Entity.User;
import com.fyj.Entity.UserPwd;
import com.fyj.header.mapper.UserMapper;
import com.fyj.header.mapper.UserPwdMapper;
import com.fyj.header.service.UserPwdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserPwdServiceImpl extends ServiceImpl<UserPwdMapper, UserPwd> implements UserPwdService {

    @Resource
    private UserPwdMapper pwdMapper;

    @Override
    public void changePwd(String userId, String password) {
        pwdMapper.changePwd(userId,password);
    }

    @Override
    public void insertMsg(String userId, String password) {
        pwdMapper.insertMsg(userId, password);
    }

    @Override
    public void deleteAllbyUserId(String userId) {
        pwdMapper.deleteAllbyUserId(userId);
    }

    @Override
    public String userIdGet(String userId) {
        return pwdMapper.userIdGet(userId);
    }

    @Override
    public String passwordGet(String userId) {
        return pwdMapper.passwordGet(userId);
    }

    @Override
    public String getPwdById(String userId) {
        return pwdMapper.getPwdById(userId);
    }


}
