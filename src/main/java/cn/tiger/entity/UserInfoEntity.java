package cn.tiger.entity;

import lombok.Data;

import java.util.List;

/**
 * 用户详细信息实体
 */
@Data
public class UserInfoEntity {
    private Integer userId; //id
    private String name; //昵称
    private String phone; //电话
    private Integer gender; //性别
    private int age; //年龄
    private String email; //邮箱
    private List<IdentityEntity> identityEntities;

}
