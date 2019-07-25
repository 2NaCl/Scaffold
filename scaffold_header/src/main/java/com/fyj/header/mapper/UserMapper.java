package com.fyj.header.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fyj.Entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface UserMapper extends Serializable, BaseMapper<User> {

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
     * 保存用户
     */

    void save(User user);

    /**
     * 注册第二步
     */




}
