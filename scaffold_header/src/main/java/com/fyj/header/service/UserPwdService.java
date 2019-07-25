package com.fyj.header.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fyj.Entity.UserPwd;

public interface UserPwdService extends IService<UserPwd> {

    String changePwd(String userId,String password);

    void insertMsg(String userId, String password);

    void deleteAllbyUserId(String userId);

}
