package cn.tiger.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 部门实体
 */
@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class DeptEntity {
    private Integer id; //id 自增
    private String name; //名称
    private Integer parentId; //父节点id
    private int status; //状态 0：正常 1：禁用
    private Date updateTime; //更新时间
    private Integer operatorId; //操作人员id
    private Date createTime; //创建时间

    private List<RoleEntity> roles; //该部门下的所有角色
}
