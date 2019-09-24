package cn.tiger.mybatis_test;

import cn.tiger.entity.DeptEntity;
import cn.tiger.mapper.DeptMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class DeptMapperTest extends BaseMapperTest<DeptMapper>{
    public DeptMapperTest() {
        super("mapper/DeptMapper.xml");
    }

    @Test
    public void addDeptTest(){
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setName("dept-test1");
        deptEntity.setParentId(0);
        deptEntity.setOperatorId(2);

        int i = super.getMapper().addDept(deptEntity);
        System.out.println(i);
    }

    @Test
    public void deleteDeptByIdTest(){
        int i = super.getMapper().deleteDeptById(1);
        System.out.println(i);
    }

    @Test
    public void updateDeptTest(){
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setId(1);
        deptEntity.setName("dept-update");
        deptEntity.setParentId(1);
        deptEntity.setOperatorId(2);

        int i = super.getMapper().updateDept(deptEntity);
        System.out.println(i);
    }

    @Test
    public void findAllTest(){
        List<DeptEntity> list = super.getMapper().findAll();
        System.out.println(list);
    }

    @Test
    public void findDeptByIdTest(){
        DeptEntity deptEntity = super.getMapper().findDeptById(1);
        System.out.println(deptEntity);
    }

    @Test
    public void findDeptByNameTest(){
        List<DeptEntity> list = super.getMapper().findDeptByName("dept");
        System.out.println(list);
    }

    @Test
    public void findDeptByDeptParentIdTest(){
        List<DeptEntity> list = super.getMapper().findDeptByDeptParentId(10);
        System.out.println(list);
    }

}
