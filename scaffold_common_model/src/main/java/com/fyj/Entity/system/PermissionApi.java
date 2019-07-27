package com.fyj.Entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "pe_permission_api")
@Getter
@Setter
public class PermissionApi implements Serializable {

    private static final long serialVersionUID = -1803315043290784820L;
    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 链接
     */
    private String apiUrl;
    /**
     * 请求类型
     */
    private String apiMethod;
    /**
     * 权限等级，1为通用接口权限，2为需校验接口权限
     */
    private String apiLevel;

}
