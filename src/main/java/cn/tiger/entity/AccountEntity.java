package cn.tiger.entity;

import lombok.Data;

import java.util.Date;

/**
 * 账号表实体
 */
@Data
public class AccountEntity {
    private Integer id; //id
    private String userName; //用户名
    private String password; //密码
    private int status; //状态 0：正常 1：禁用
    private Date createTime; //创建时间
}
