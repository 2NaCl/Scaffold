package com.fyj.header.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fyj.Entity.User;
import com.fyj.common.entity.Result;
import com.fyj.common.entity.ResultCode;
import com.fyj.common.utils.IdWorkers;
import com.fyj.header.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private IdWorkers idWorkers;

    @GetMapping(value = "/all")
    public Result findAll() {
        List<User> list = userService.findAll();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(list);
        return result;
    }


}
