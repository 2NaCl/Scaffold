package com.fyj.Entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "pe_permission_menu")
@Getter
@Setter
public class PermissionMenu implements Serializable {


    /**
     * 主键
     */
    @TableId
    private String id;

    //展示图标
    private String menuIcon;

    //排序号
    private String menuOrder;

}
