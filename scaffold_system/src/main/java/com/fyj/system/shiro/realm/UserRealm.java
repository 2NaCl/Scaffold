package com.fyj.system.shiro.realm;

import com.fyj.common.shiro.realm.HeaderRealm;
import com.fyj.header.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends HeaderRealm {

    @Autowired
    private UserService userService;


}
