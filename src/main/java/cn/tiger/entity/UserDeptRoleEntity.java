package cn.tiger.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * create by yifeng
 */
@Data
@TableName("t_user_dept_role")
public class UserDeptRoleEntity extends Model<UserDeptRoleEntity> {
    @TableId
    private Integer id;
    private Integer userId;
    private Integer DeptRoleId;
}
