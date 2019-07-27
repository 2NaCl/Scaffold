package com.fyj.Entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

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
}
