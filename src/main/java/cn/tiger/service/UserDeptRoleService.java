package cn.tiger.service;

import cn.tiger.dto.DeptTree;
import cn.tiger.dto.TreeNode;
import cn.tiger.entity.*;
import cn.tiger.mapper.DeptRoleMapper;
import cn.tiger.mapper.UserDeptRoleMapper;
import cn.tiger.vo.TreeUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * create by yifeng
 */
@Service
public class UserDeptRoleService {
    @Autowired
    private UserDeptRoleMapper userDeptRoleMapper;
    @Autowired
    private DeptRoleMapper deptRoleMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private DeptService deptService;

    /**
     * 获取用户可发送消息的顶级部门
     * TODO 未考虑该顶级部门为当前用户的子部门的情况
     * @param roleId
     * @param deptId
     * @return
     */
    public DeptEntity findAuthOrganization(Integer roleId, Integer deptId) {
        RoleEntity roleEntity = roleService.getById(roleId);
        // 角色等级 0：则只能发送本部门及其以下， 1：则可以发送父部门及父部门的子部门，以此类推
        int i = roleEntity.getLevel();
        DeptEntity deptEntity = deptService.findById(deptId);
        if (i == 0) { // TODO 0 标识当前机构，建议将level标识 设置为常量
            return deptEntity;
        }
        DeptEntity result = new DeptEntity();
        result.setParentId(deptEntity.getParentId());
        for (int j = 0; j < i; j++) {
            result.setId(result.getParentId());
            DeptEntity temp = deptEntity.selectById(result);
            if (temp == null && j == 0) { // j==null, 则表示当前用户所在部门没有父部门，直接返回当前用户的部门
                return deptEntity;
            }
            if (temp == null) { // 若仅仅是temp为null，则表示temp没有父部门，直接返回即可
                return result;
            }
            result = temp;
        }
        return result;
    }

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

    /**
     * 获取部门及其子部门下所有用户
     * @return
     */
    public Set<UserInfoEntity> queryAllUserUnderTheDept(Integer deptId) {
        DeptTree deptTreeNode = new DeptTree();
        deptTreeNode.setId(deptId);
        List<DeptEntity> deptEntityList = deptService.findAll();
        DeptTree deptTree = TreeUtil.findChildren(deptTreeNode, deptService.buildDeptTreeNodeList(deptEntityList));
//        Set<UserInfoEntity> userInfoEntitySet = new HashSet<>();
//        DeptTree deptTree = deptService.findTreeByDeptId(deptTreeNode, deptService.getTree());
        List<DeptTree> deptTreeList = new ArrayList(1);
        deptTreeList.add(deptTree);
        return traversingTree(deptTreeList);
    }

    /**
     * 查询部门角色是否存在
     * @return 部门角色关系
     */
    public DeptRoleEntity findDeptRoleExist(Integer roleId, Integer deptId) {
        DeptRoleEntity deptRoleEntity = new DeptRoleEntity();
        deptRoleEntity.setRoleId(roleId);
        deptRoleEntity.setDeptId(deptId);
        DeptRoleEntity selectOne = deptRoleEntity.selectOne(Wrappers.lambdaQuery(deptRoleEntity));
        return selectOne;
    }

    /**
     * 增加用户-部门角色关系
     * @param userId
     * @param identityId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int addIdentityForUser(Integer userId, Integer identityId) {
        return userDeptRoleMapper.addIdentityForUser(userId, identityId);
    }


    /**
     * 删除用户-部门角色关系
     * @param identityId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int delIdentityForId(Integer identityId) {
        return userDeptRoleMapper.deleteIdentityById(identityId);
    }

    /**
     * 删除部门角色关系
     * @param deptId 部门唯一标识号
     * @return
     */
    public int delDeptRoleByDeptId(Integer deptId) {
        return deptRoleMapper.deleteDeptRoleByDeptId(deptId);
    }

    /**
     * 删除用户-部门角色关系
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int delIdentityForUser(Integer userId) {
        return userDeptRoleMapper.deleteIdentityByUserId(userId);
    }

    /**
     * 递归实现查找树下所有用户
     * @param deptTreeList 树列表
     * @param <T> 树节点
     * @return
     */
    private <T extends TreeNode> Set<UserInfoEntity> traversingTree(List<T> deptTreeList) {
        Set<UserInfoEntity> resultSet = null;
        for (int i = 0; i < deptTreeList.size(); i++) {
            T deptTree = deptTreeList.get(i);
            resultSet = new HashSet(findUserInfoByDeptId(deptTree.getId()));
            if (deptTree.getChildren() == null || deptTree.getChildren().size() <= 0) {
                return resultSet;
            }
            resultSet.addAll(traversingTree(deptTree.getChildren()));
            return resultSet;
        }

        return resultSet;
    }

}
