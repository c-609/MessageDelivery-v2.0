package cn.tiger.service;

import cn.tiger.entity.RoleEntity;
import cn.tiger.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * create by yifeng
 */
@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public RoleEntity getById(Integer id) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(id);
        return roleEntity.selectById();
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer addRole(RoleEntity role) {
        if (role != null) {
            return roleMapper.addRole(role);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        roleMapper.deleteRoleById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(RoleEntity role) {
        roleMapper.updateRole(role);
    }

    /**
     * 通过用户id查找对应的角色列表
     * @param uid
     */
    public void getRoleListByUserId(Integer uid) {
        roleMapper.findRoleByUserId(uid);
    }
}
