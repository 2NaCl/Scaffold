package com.fyj.Entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;


@TableName(value = "pe_permission")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Permission {

    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限类型 1为菜单 2为功能 3为API
     */
    private Integer type;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 权限描述
     */
    private String description;

    private String pid;

    //可见状态
    private String enVisible;

    public Permission(String name, Integer type, String code, String description) {
        this.name = name;
        this.type = type;
        this.code = code;
        this.description = description;
    }

}
