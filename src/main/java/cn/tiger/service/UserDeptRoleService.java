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

}
