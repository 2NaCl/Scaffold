package com.fyj.header.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fyj.Entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 通过用户id查询用户详细信息
     * @param userId
     * @return
     */
    User findByUserid(String userId);

    /**
     * 利用userId更新用户
     * @param userId
     */

    void updateLevelByUserid(String userId,String level);

    /**
     * 删除用户
     */
    void deleteByUserId(String userId);

    /**
     * 注册第二步
     * @param user
     */
    void saveAll(User user);

    /**
     * 查找用户是否已经注册
     */
    String findId(String userId);
}
