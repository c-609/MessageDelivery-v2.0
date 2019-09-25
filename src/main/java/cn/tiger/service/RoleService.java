package cn.tiger.service;

import cn.tiger.entity.RoleEntity;
import cn.tiger.mapper.RoleMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * create by yifeng
 */
@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public RoleEntity getById(Integer id) {
        if (id == null || id.intValue() <= 0)
            return null;
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(id);
        return roleEntity.selectById();
    }

    public Page<List<RoleEntity>> getList(Page page) {
        return roleMapper.findAll(page);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean insertOrUpdate(RoleEntity role) {
        if (role != null) {
            RoleEntity roleEntity = new RoleEntity();
            BeanUtils.copyProperties(role, roleEntity);
            return roleEntity.insertOrUpdate();
        }
        return Boolean.FALSE;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        roleMapper.deleteRoleById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean update(RoleEntity role) {
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(role, roleEntity);
        return roleEntity.update(Wrappers.update());
    }

    /**
     * 通过用户id查找对应的角色列表
     * @param uid
     */
    public void getRoleListByUserId(Integer uid) {
        roleMapper.findRoleByUserId(uid);
    }
}
