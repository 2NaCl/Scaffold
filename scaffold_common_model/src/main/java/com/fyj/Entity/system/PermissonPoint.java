package com.fyj.Entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "pe_permission_point")
@Getter
@Setter
public class PermissonPoint implements Serializable {

    private static final long serialVersionUID = -1002411490113957485L;

    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     * 权限代码
     */
    private String pointClass;

    private String pointIcon;

    private String pointStatus;

}
