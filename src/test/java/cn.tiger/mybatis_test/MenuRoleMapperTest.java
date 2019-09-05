package cn.tiger.mybatis_test;

import cn.tiger.mapper.MenuRoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MenuRoleMapperTest extends BaseMapperTest<MenuRoleMapper>{
    public MenuRoleMapperTest() {
        super("mapper/MenuRoleMapper.xml");
    }

    @Test
    public void addAuthorizationForRoleTest(){
        Integer[] menuIds = new Integer[]{1000,1001};
        int i = super.getMapper().addAuthorizationForRole(1,menuIds);
        System.out.println(i);
    }
    @Test
    public void deleteAuthorizationById(){
        int i = super.getMapper().deleteAuthorizationById(1);
        System.out.println(i);
    }
}
