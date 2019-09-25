package cn.tiger.mybatis_test;

import cn.tiger.entity.RoleEntity;
import cn.tiger.mapper.RoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class RoleMapperTest extends BaseMapperTest<RoleMapper>{
    public RoleMapperTest() {
        super("mapper/RoleMapper.xml");
    }

    @Test
    public void addRoleTest(){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("test1_name");
        roleEntity.setRoleName("ROLE_test1");
        roleEntity.setOperatorId(2);
        int i = super.getMapper().addRole(roleEntity);
        System.out.println(i);
    }

    @Test
    public void deleteRoleByIdTest(){
        int i = super.getMapper().deleteRoleById(1);
        System.out.println(i);
    }
    @Test
    public void updateRoleTest(){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1);
        roleEntity.setName("update_name");
        roleEntity.setRoleName("ROLE_test1_update");

        int i = super.getMapper().updateRole(roleEntity);
        System.out.println(i);
    }

//    @Test
//    public void findAllTest(){
//        List<RoleEntity> list = super.getMapper().findAll();
//        System.out.println(list);
//    }

    @Test
    public void findRoleByDeptIdTest(){
        List<RoleEntity> list = super.getMapper().findRoleByDeptId(1);
        System.out.println(list);
    }
}
