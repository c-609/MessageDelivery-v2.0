package cn.tiger.controller.manage;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.common.core.util.R;
import cn.tiger.entity.DeptEntity;
import cn.tiger.entity.DeptRoleEntity;
import cn.tiger.entity.UserInfoEntity;
import cn.tiger.service.DeptService;
import cn.tiger.service.UserDeptRoleService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * create by yifeng
 */
@RestController
@RequestMapping("/manage/dept")
public class ManageDeptController {

    @Autowired
    private DeptService deptService;
    @Autowired
    private UserDeptRoleService userDeptRoleService;

    /**
     * 获取树的根节点
     * @return
     */
    @RequestMapping("/tree/root")
    public R findTreeRoot() {
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setParentId(0);
        // 默认
        return new R(deptEntity.selectOne(Wrappers.lambdaQuery()));
    }

    /**
     * 获取整颗树
     * @return
     */
    @GetMapping("/tree")
    public R findTree() {
        return new R(deptService.getTree());
    }

    /**
     * 添加部门
     * @param deptEntity
     * @return
     */
    @PostMapping
    public R save(@Valid @RequestBody DeptEntity deptEntity) {
        deptService.save(deptEntity);
        return R.builder().msg("添加成功").build();
    }

    @PutMapping
    public R update(@Valid @RequestBody DeptEntity deptEntity) {
        deptEntity.setUpdateTime(LocalDateTime.now());
        deptEntity.updateById();
        return R.builder().msg("修改成功").build();
    }

    /**
     * 删除部门
     * @param deptId
     * @return
     */
    @DeleteMapping
    public R removeById(Integer deptId) {
        List<DeptEntity> deptEntityList = deptService.findChilderNode(deptId);
        // 删除前检查是否存在子部门
        if(deptEntityList != null && deptEntityList.size() > 0) {
            return R.builder().msg("删除失败，请先删除子部门").build();
        }
        // 删除前检查是否存在用户部门关系。删除部门角色关系前，需要先确认用户-部门角色关系
        List<UserInfoEntity> userInfoEntityList = userDeptRoleService.findUserInfoByDeptId(deptId);
        if (userInfoEntityList != null && userInfoEntityList.size() > 0) {
                return R.builder().msg("删除失败，存在用户关联该部门").build();
        }

        // 删除部门角色关系
        userDeptRoleService.delDeptRoleByDeptId(deptId);
        // 删除部门
        deptService.delete(deptId);
        return R.builder().msg("删除成功").build();
    }

    /**
     * 获取用户的可发送消息的顶级部门
     * @param roleId
     * @param deptId
     * @return
     */
    @GetMapping("/get_user_top_organization")
    public R getUserTopOrganization(Integer roleId, Integer deptId) {
        if (roleId == null || roleId.intValue() <= 0 || deptId == null || deptId.intValue() <= 0) {
            return R.builder().msg("参数不正确").code(CommonConstants.PARAMETER_ERROR).build();
        }
        DeptRoleEntity selectOne =
                userDeptRoleService.findDeptRoleExist(roleId, deptId);
        if (selectOne == null)
            return R.builder().msg("不存在所选择的职位，请刷新").code(CommonConstants.PARAMETER_ERROR).build();
        return new R(userDeptRoleService.findAuthOrganization(roleId, deptId));
    }

    /**
     * 获取部门的子节点
     * @param id
     * @return
     */
    @GetMapping("/child/{id}")
    public R getChild(@PathVariable Integer id) {
        if (id == null || id.intValue() <= 0) {
            return R.builder().msg("参数不正确").code(CommonConstants.PARAMETER_ERROR).build();
        }
        List<DeptEntity> deptEntityList = deptService.findChilderNode(id);
        if (deptEntityList == null || deptEntityList.size() <= 0) {
            return R.builder().msg("无子节点").build();
        }
        return new R(deptService.findChilderNode(id));
    }

}
