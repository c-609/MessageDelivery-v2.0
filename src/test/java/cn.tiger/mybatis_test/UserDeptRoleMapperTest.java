package cn.tiger.mybatis_test;

import cn.tiger.entity.IdentityEntity;
import cn.tiger.mapper.UserDeptRoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class UserDeptRoleMapperTest extends BaseMapperTest<UserDeptRoleMapper>{
    public UserDeptRoleMapperTest() {
        super("mapper/UserDeptRoleMapper.xml");
    }

    @Test
    public void addIdentityForUserTest(){
        int i = super.getMapper().addIdentityForUser(2,1);
        System.out.println(i);
    }

    @Test
    public void deleteIdentityByIdTest(){
        int i = super.getMapper().deleteIdentityById(1);
        System.out.println(i);
    }

    @Test
    public void updateIdentityTest(){
        int i = super.getMapper().updateIdentity(1,2);
        System.out.println(i);
    }
    @Test
    public void findIdentityByUserIdTest(){
        List<IdentityEntity> list = super.getMapper().findIdentityByUserId(2);
        System.out.println(list);
    }
}
