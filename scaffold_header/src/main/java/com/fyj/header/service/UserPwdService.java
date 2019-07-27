package com.fyj.header.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fyj.Entity.User;
import com.fyj.Entity.UserPwd;

public interface UserPwdService extends IService<UserPwd> {


    /**
     * 修改密码
     * @param userId
     * @param password
     * @return
     */
    void changePwd(String userId,String password);

    /**
     * 注册第一步
     *
     * @return
     */
    void insertMsg(String userId, String password);

    /**
     * 删除这个人信息
     * @param userId
     */
    void deleteAllbyUserId(String userId);

    /**
     * 检查是否有此账号
     *
     * @param userId
     * @return
     */
    String userIdGet(String userId);

    /**
     * 获取到此人密码
     */

    String passwordGet(String userId);
    /**
     * 用账号获取密码
     */
    String getPwdById(String userId);

}
