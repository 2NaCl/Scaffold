package com.fyj.header.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fyj.Entity.User;
import com.fyj.header.mapper.UserMapper;
import com.fyj.header.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Resource
    private UserMapper userMapper;


    public List<User> findAll() {
        return this.userMapper.findAll();
    }

    @Override
    public User findByUserid(String userId) {
        return this.userMapper.findByUserid(userId);
    }

    @Override
    public void updateLevelByUserid(String userId, String level) {
        this.userMapper.updateLevelByUserid(userId,level);
    }

    @Override
    public void deleteByUserId(String userId) {
        this.userMapper.deleteByUserId(userId);
    }

    @Override
    public void saveAll(User user) {
        userMapper.save(user);
    }

    @Override
    public String findId(String userId) {
        String id = userMapper.findId(userId);
        return id;
    }


}
