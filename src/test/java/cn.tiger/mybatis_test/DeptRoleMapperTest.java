package cn.tiger.mybatis_test;

import cn.tiger.entity.DeptEntity;
import cn.tiger.mapper.DeptRoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class DeptRoleMapperTest extends BaseMapperTest<DeptRoleMapper>{
    public DeptRoleMapperTest() {
        super("mapper/DeptRoleMapper.xml");
    }

    @Test
    public void addDeptRoleTest(){
        Integer[] roleIds = new Integer[]{1,2};
        int i = super.getMapper().addDeptRole(1,roleIds);
        System.out.println(i);
    }
    @Test
    public void deleteDeptRoleByDeptIdTest(){
        int i = super.getMapper().deleteDeptRoleByDeptId(1);
        System.out.println(i);
    }

    @Test
    public void findAllTest(){
        List<DeptEntity> list = super.getMapper().findAll();
        System.out.println(list);
    }
}
