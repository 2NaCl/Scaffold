package com.fyj.header.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fyj.Entity.User;
import com.fyj.Entity.UserPwd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

@Mapper
public interface UserPwdMapper extends Serializable, BaseMapper<UserPwd> {

    /**
     * 修改密码
     * @param userId
     * @param password
     * @return
     */
    String changePwd(String userId,String password);

    /**
     * 注册第一步
     *
     * @return
     */
    void insertMsg(@Param("userId") String userId, @Param("password") String password);

    /**
     * 删除账号密码信息
     * @param userId
     */
    void deleteAllbyUserId(String userId);

}
