package com.changes.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiuJunJie
 * @since 2019/11/21 15:51
 */
@TableName("user")
@Data
public class User {

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;
}
