package cn.tiger.entity;

import lombok.Data;

import java.util.List;

/**
 *  部门和角色对应实体
 */
@Data
public class IdentityEntity {
    private Integer id;//用户部门角色关联表的id 自增

    private Integer deptRoleId; //部门与角色对应关系id

    private Integer deptId; //部门id
    private String deptName; //部门名称

    private Integer roleId; //角色id
    private String roleName; //角色英文名 ROLE_
    private String roleCNName; //角色中文名称

}
