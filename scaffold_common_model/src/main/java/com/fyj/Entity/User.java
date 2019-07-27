package com.fyj.Entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fyj.Entity.system.Role;
import lombok.*;
import org.apache.ibatis.annotations.Many;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@TableName(value = "sf_user")
public class User {

    @TableId
    private String id;

    private String userId;

    private String phone;

    private String email;

    private String city;

    private String home;

    private String idCard;

    private String birthday;

    private String loginDate;

    private String level;

    /**
     * pe_user_role联合属性 用户-角色 多对多
     */
    private Set<Role> roles = new HashSet<>();


}
