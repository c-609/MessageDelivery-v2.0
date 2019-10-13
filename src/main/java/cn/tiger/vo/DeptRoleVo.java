package cn.tiger.vo;

import cn.tiger.entity.DeptEntity;
import cn.tiger.entity.RoleEntity;
import lombok.Data;

/**
 * create by yifeng
 */
@Data
public class DeptRoleVo {
    private Integer deptRoleId;
    private DeptEntity deptEntity;
    private RoleEntity roleEntity;
}
