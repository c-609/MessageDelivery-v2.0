package cn.tiger.controller.manage;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.common.core.util.R;
import cn.tiger.dto.DeptTree;
import cn.tiger.dto.UserDto;
import cn.tiger.entity.AccountEntity;
import cn.tiger.entity.DeptRoleEntity;
import cn.tiger.entity.IdentityEntity;
import cn.tiger.entity.UserInfoEntity;
import cn.tiger.mapper.UserDeptRoleMapper;
import cn.tiger.service.DeptService;
import cn.tiger.service.UserDeptRoleService;
import cn.tiger.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 用户控制类
 * create by yifeng
 */
@RestController
@RequestMapping("/manage/user")
public class ManageUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDeptRoleService userDeptRoleService;
    @Autowired
    private DeptService deptService;

    @GetMapping("/page")
    public R getUserPage(Page page) {
        return new R(userService.getUserPage(page));
    }

    /**
     * 获取用户详情
     * @param userId 用户唯一标识
     * @return
     */
    @GetMapping("/get_user_information")
    public R getUserInfo(Integer userId) {
        if (userId == null) {
            return R.builder().msg("用户id不能为空").code(CommonConstants.PARAMETER_ERROR).build();
        }
        UserInfoEntity userInfoEntity = userService.getIdByUserInfo(userId);
        if (userInfoEntity == null) {
            return R.builder().msg("该用户不存在").build();
        }
        return new R(userInfoEntity);
    }

    /**
     * 修改用户
     * @param userDto
     * @return
     */
    @PutMapping
    public R updateUser(@RequestBody UserDto userDto) {
        return new R(userService.saveUser(userDto));
    }

    /**
     * 添加用户
     * @param userDto
     * @return
     */
    @PostMapping
    public R User(@RequestBody UserDto userDto){
        // 查询对应的部门角色关系是否存在
        List<IdentityEntity> identityEntityList = userDto.getIdentityEntities();
        if (identityEntityList == null || identityEntityList.size() <= 0) {
            return R.builder().msg("请选择部门").code(CommonConstants.PARAMETER_ERROR).build();
        }
        for (int i = 0; i < identityEntityList.size(); i++) {
            IdentityEntity identityEntity = identityEntityList.get(i);
            DeptRoleEntity selectOne =
                    userDeptRoleService.findDeptRoleExist(identityEntity.getRoleId(), identityEntity.getDeptId());
            if (selectOne == null)
                return R.builder().msg("不存在所选择的职位，请刷新").code(CommonConstants.PARAMETER_ERROR).build();
        }
        boolean result = userService.addUser(userDto);
        if (result) {
            return R.builder().msg("添加成功").build();
        }
        return R.builder().msg("添加失败").build();
    }

    @DeleteMapping
    public R delete(Integer userId) {
        userService.delete(userId);
        return R.builder().msg("删除成功").build();
    }


    /**
     * 通过部门id获取用户信息列表
     * @param deptId 部门唯一标识
     * @return
     */
    @GetMapping("/dept")
    public R getUserInfoByDeptId(Integer deptId) {
        List<UserInfoEntity> userInfoEntityList = userDeptRoleService.findUserInfoByDeptId(deptId);
        if (userInfoEntityList == null || userInfoEntityList.size() <= 0) {
            return R.builder().msg("该部门下没有用户").build();
        }
        return new R(userInfoEntityList);
    }

    /**
     * 获取部门下所有用户
     * @param deptId
     * @return
     */
    @GetMapping("/dept_under/{deptId}")
    public R getAllByDeptId(@PathVariable Integer deptId) {
        Set<UserInfoEntity> userInfoEntityList = userDeptRoleService.queryAllUserUnderTheDept(deptId);
        if (userInfoEntityList == null || userInfoEntityList.size() <= 0) {
            return R.builder().msg("该部门下没有用户").build();
        }
        return new R(userInfoEntityList);
    }



}
