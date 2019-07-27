package com.fyj.Entity.system;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fyj.Entity.User;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "pe_role")
public class Role {

    @TableId
    private String id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 说明
     */
    private String description;

    /**
     * 用户-角色 多对多
     */
    private Set<User> users = new HashSet<>();

    /**
     * 角色-权限 多对多
     */
    private Set<Permission> permissions = new HashSet<>();


}
