package cn.tiger.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.management.relation.Role;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色实体
 */
@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@TableName("sys_role")
public class RoleEntity extends Model<RoleEntity> {
    @TableId
    private Integer id; //角色id
    private String roleName; //角色名 ROLE_
    private String name; // 中文名
    private Integer status; //状态 0：正常 1：禁用
    private Integer level; // 角色发送消息到达的等级
    private String levelName;
    private Date updateTime; //最后一次更新时间
    private Integer operatorId; //操作人员id
    private Date createTime; //创建时间

    @Override
    protected Serializable pkVal() {
        return super.pkVal();
    }
}
