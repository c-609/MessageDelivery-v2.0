package cn.tiger.service;

import cn.tiger.entity.IdentityEntity;
import cn.tiger.entity.UserInfoEntity;
import cn.tiger.mapper.UserDeptRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by yifeng
 */
@Service
public class UserDeptRoleService {
    @Autowired
    private UserDeptRoleMapper userDeptRoleMapper;
    @Autowired
    private UserService userService;


    public List<IdentityEntity> findByUid(Integer uid) {
        if (uid == null) {
            return null;
        }
        return userDeptRoleMapper.findIdentityByUserId(uid);
    }

    public int findUserDeptRole(Integer deptId, Integer roleId, Integer usreId) {
        List<IdentityEntity> identityEntityList = findByUid(usreId);
        if (identityEntityList == null || identityEntityList.size() <= 0) {
            return 0;
        }
        for (IdentityEntity entity : identityEntityList) {
            if (entity.getDeptId() == deptId && entity.getRoleId() == roleId) {
                return entity.getId();
            }
        }
        return 0;
    }

    /**
     * 通过部门id查用户
     * @param deptId
     * @return
     */
    public List<UserInfoEntity> findUserInfoByDeptId(Integer deptId) {
        if (deptId == null) {
            return null;
        }
        return userService.findUserListByDeptId(deptId);
    }

}
