package cn.tiger.service;

import cn.tiger.dto.DeptTree;
import cn.tiger.dto.TreeNode;
import cn.tiger.entity.DeptEntity;
import cn.tiger.entity.IdentityEntity;
import cn.tiger.entity.RoleEntity;
import cn.tiger.mapper.DeptMapper;
import cn.tiger.vo.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * create by yifeng
 */
@Service
public class DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private UserDeptRoleService userDeptRoleService;

    /**
     * 根据用户id查找部门列表
     * @param uid 用户唯一标识
     * @return
     */
    public List<DeptEntity> findByUid(Integer uid) {
        List<IdentityEntity> identityEntityList = userDeptRoleService.findByUid(uid);
        List<DeptEntity> deptEntityList = new ArrayList();
        if (identityEntityList == null) {
            return null;
        }
        identityEntityList.forEach(identity -> deptEntityList.add(deptMapper.findDeptById(identity.getDeptId())));
        return deptEntityList;
    }

    /**
     * 根据部门id查找部门实体
     * @param deptId 部门唯一标识
     * @return
     */
    public DeptEntity findById(Integer deptId) {
        return deptMapper.findDeptById(deptId);
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer addDept(DeptEntity dept) {
        if (dept != null) {
            return deptMapper.addDept(dept);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        deptMapper.deleteDeptById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(DeptEntity dept) {
        deptMapper.updateDept(dept);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(DeptEntity dept) {
        deptMapper.addDept(dept);
    }



    /**
     * 获取部门的子部门
     * @param deptId
     * @return
     */
    public List<DeptEntity> findChilderNode(int deptId) {
        if (deptId <= 0) {
            return null;
        }
        List<DeptEntity> deptEntityList = deptMapper.findDeptByDeptParentId(deptId);
        return deptEntityList;
    }

    /**
     * 获取用户的部门树列表
     * @param deptEntityList 用户的部门列表
     * @param userId 用户唯一标识
     * @param completeTree 完整的部门树
     * @return
     */
    public List<DeptTree> findTreeByUserDept(List<DeptEntity> deptEntityList, List<DeptTree> completeTree, Integer userId) {
        // 先找到无上下级关系的部门节点
        List<DeptTree> nonRelationDeptNode = TreeUtil.findTreeNodeOrNonSuperordinateRelationship(buildDeptTreeNodeList(deptEntityList), completeTree);
        List<DeptTree> deptTreeList = new ArrayList<>(nonRelationDeptNode.size());
        // 将各个部门节点进行建树
        nonRelationDeptNode.forEach(item -> deptTreeList.add(findTreeByDeptId(item, completeTree)));
        return deptTreeList;
    }

    public List<DeptTree> findTreeByUserDept(List<DeptEntity> deptEntityList, Integer userId) {
        return findTreeByUserDept(deptEntityList, getTree(), userId);
    }

    public List<DeptTree> findTreeByUserDept(Integer userId) {
        return findTreeByUserDept(findByUid(userId), userId);
    }

    public List<DeptEntity> findAll() {
        return deptMapper.findAll();
    }

    /**
     *
     * @return 整颗部门树
     */
    public List<DeptTree> getTree() {
        List<DeptEntity> deptEntityList = findAll();
        return getDeptTree(deptEntityList, 0); // 父节点为-1
    }

    /**
     * 查找部门子树
     * @param deptTree
     * @param complateTree
     * @return
     */
    public DeptTree findTreeByDeptId(DeptTree deptTree, List<DeptTree> complateTree) {
        return TreeUtil.findChildren(deptTree, complateTree);
    }

    public DeptTree ConversionByTreeNode(DeptEntity dept) {
        DeptTree deptTree = new DeptTree();
        deptTree.setId(dept.getId());
        return deptTree;
    }

    /**
     * 构建部门树节点列表，未建树
     * @param deptList
     * @return
     */
    public List<DeptTree> buildDeptTreeNodeList(List<DeptEntity> deptList) {
        List<DeptTree> resultList = new ArrayList();
        deptList.forEach(dept -> {
            DeptTree node = new DeptTree();
            node.setId(dept.getId());
            node.setParentId(dept.getParentId());
            node.setName(dept.getName());
            resultList.add(node);
        });
        return resultList;
    }

    /**
     * 构建部门树
     *
     * @param depts
     * @return
     */
    private List<DeptTree> getDeptTree(List<DeptEntity> depts, int root) {
        List<DeptTree> treeList = depts.stream()
                .filter(dept -> !dept.getId().equals(dept.getParentId()))
                .map(dept -> {
                    DeptTree node = new DeptTree();
                    node.setId(dept.getId());
                    node.setParentId(dept.getParentId());
                    node.setName(dept.getName());
                    return node;
                }).collect(Collectors.toList());
        return TreeUtil.buildByLoop(treeList, root);
    }

    /**
     * 提取部门列表中的id
     * @param deptEntityList
     * @return
     */
    private List<Integer> conversionDeptListToIdList(List<DeptEntity> deptEntityList) {
        List<Integer> result = new ArrayList<>(deptEntityList.size());
        for (DeptEntity deptEntity : deptEntityList) {
            result.add(deptEntity.getId());
        }
        return result;
    }
 }
