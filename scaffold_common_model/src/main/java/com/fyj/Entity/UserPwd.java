package com.fyj.Entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
@Getter
@TableName(value = "sf_userpwd")
public class UserPwd {

    @TableId
    private String userId;

    private String password;

}
