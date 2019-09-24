package cn.tiger.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * create by yifeng
 */
@TableName("t_dept_role")
@Data
public class DeptRoleEntity extends Model<DeptRoleEntity> {
    private Integer id;
    private Integer deptId;
    private Integer roleId;
}
