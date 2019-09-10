package cn.tiger.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.management.relation.Role;
import java.util.Date;

/**
 * 角色实体
 */
@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@TableName("sys_role")
public class RoleEntity extends Model<RoleEntity> {
    private Integer id; //角色id
    private String roleName; //角色名 ROLE_
    private String name; // 中文名
    private int status; //状态 0：正常 1：禁用
    private Date updateTime; //最后一次更新时间
    private Integer operatorId; //操作人员id
    private Date createTime; //创建时间
}
