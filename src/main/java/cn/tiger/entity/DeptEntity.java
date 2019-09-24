package cn.tiger.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 部门实体
 */
@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@TableName("t_dept")
public class DeptEntity extends Model<DeptEntity> {
    @TableId(type = IdType.AUTO)
    private Integer id; //id 自增
    @NotNull
    private String name; //名称
    private Integer parentId; //父节点id
    private Integer status; //状态 0：正常 1：禁用
    private LocalDateTime updateTime; //更新时间
    private Integer operatorId; //操作人员id
    private LocalDateTime createTime; //创建时间
    @TableField(exist = false)
    private List<RoleEntity> roles; //该部门下的所有角色
}
