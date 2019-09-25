package cn.tiger.controller.manage;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.common.core.util.R;
import cn.tiger.entity.RoleEntity;
import cn.tiger.service.RoleService;
import cn.tiger.service.UserDeptRoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * 角色控制类
 * create by yifeng
 */
@RestController
@RequestMapping("/manage/role")
public class ManageRoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserDeptRoleService userDeptRoleService;

    /**
     * 获取角色 通过id
     * @param id 角色唯一标识
     * @return
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        return new R(roleService.getById(id));
    }

    /**
     * 分页查询角色列表
     * @param page
     * @return
     */
    @GetMapping("/page")
    public R getList(Page page) {
        return new R(roleService.getList(page));
    }

    /**
     * 增加用户
     * @param roleEntity
     * @return
     */
    @PostMapping
    public R addRole(@Valid @RequestBody RoleEntity roleEntity) {
        return new R(roleService.insertOrUpdate(roleEntity));
    }

    /**
     * 更新用户
     * @param roleEntity
     * @return
     */
    @PutMapping
    public R updateRole(@Valid @RequestBody RoleEntity roleEntity) {
        if (roleEntity.getId() == null || roleEntity.getId().intValue() <= 0) {
            return R.builder().msg("修改失败，角色唯一标识不能为空").code(CommonConstants.PARAMETER_ERROR).build();
        }
        roleEntity.setUpdateTime(LocalDateTime.now());
        return new R(roleService.insertOrUpdate(roleEntity));
    }

    /**
     * 删除角色 考虑到当前角色并没有与用户产生直接关联，故没有判断角色与用户管理的情况
     * @param roleId 角色唯一标识
     * @return
     */
    @DeleteMapping
    public R deleteById(Integer roleId) {
        // 判断角色是否与部门关联
        boolean exist = userDeptRoleService.findDeptRoleExist(roleId, null);
        if (exist)
            return R.builder().msg("该角色与部门关联，请先删除").build();
        roleService.delete(roleId);
        return new R(true);
    }

}
