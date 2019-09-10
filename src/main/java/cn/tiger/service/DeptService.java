package cn.tiger.service;

import cn.tiger.dto.DeptTree;
import cn.tiger.dto.TreeNode;
import cn.tiger.entity.DeptEntity;
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


    /**
     *
     * @return 整颗部门树
     */
    public List<DeptTree> getTree() {
        List<DeptEntity> deptEntityList = deptMapper.findAll();
        return getDeptTree(deptEntityList, -1); // 父节点为-1
    }

    /**
     * 查找部门子树
     * @return
     */
    public DeptTree getTreeByDeptId(Integer id) {
        List<DeptTree> tree = getDeptTree(deptMapper.findAll(), -1);
        DeptTree deptTree = new DeptTree();
        deptTree.setId(id);
        return TreeUtil.findChildren(deptTree, tree);
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
}
