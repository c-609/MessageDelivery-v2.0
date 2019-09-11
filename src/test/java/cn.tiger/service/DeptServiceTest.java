package cn.tiger.service;

import cn.tiger.MessageDeliveryApplicationTest;
import cn.tiger.dto.DeptTree;
import cn.tiger.dto.TreeNode;
import cn.tiger.entity.DeptEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * create by yifeng
 */
public class DeptServiceTest extends MessageDeliveryApplicationTest {
    @Autowired
    private DeptService deptService;

    @Test
    public void testTree() {
        List<DeptTree> treeNodes = deptService.getTree();
        System.out.println(treeNodes);
    }

    @Test
    public void findTreeByUserDept() {
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setId(2);
        deptEntity.setParentId(1);
        DeptEntity deptEntity2 = new DeptEntity();
        deptEntity2.setId(3);
        deptEntity2.setParentId(1);
        List<DeptTree> deptTreeList = deptService.findTreeByUserDept(Arrays.asList(deptEntity, deptEntity2), 2);
        for (DeptTree deptTree : deptTreeList) {
            System.out.println(deptTree);
        }
    }

//    @Test
//    public void findTreeByDeptId() {
//        DeptTree deptTree1 = new DeptTree();
//        deptTree1.setId(2);
//        DeptTree deptTree = deptService.findTreeByDeptId(deptTree1);
//        System.out.println(deptTree);
//    }

}
