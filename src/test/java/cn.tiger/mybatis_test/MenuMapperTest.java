package cn.tiger.mybatis_test;

import cn.tiger.entity.MenuEntity;
import cn.tiger.mapper.MenuMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class MenuMapperTest extends BaseMapperTest<MenuMapper>{
    public MenuMapperTest() {
        super("mapper/MenuMapper.xml");
    }

    @Test
    public void addMenuTest(){
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setName("test1-menu");
        menuEntity.setParentId(-1);
        menuEntity.setId(1000);
        menuEntity.setPath("/test1");

        int i = super.getMapper().addMenu(menuEntity);
        System.out.println(i);
    }

    @Test
    public void deleteMenuByIdTest(){
        int i = super.getMapper().deleteMenuById(1000);
    }

    @Test
    public void updateMenuTest(){
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setName("update-menu");
        menuEntity.setId(1000);
        menuEntity.setPath("/test1/update");
        int i = super.getMapper().updateMenu(menuEntity);
        System.out.println(i);
    }

    @Test
    public void findAllTest(){
        List<MenuEntity> list = super.getMapper().findAll();
        System.out.println(list);
    }
    @Test
    public void findMenuByUserIdTest(){
        List<MenuEntity> list = super.getMapper().findMenuByUserId(2);
        System.out.println(list);
    }
    @Test
    public void findMenuByRoleIdTest(){
        List<MenuEntity> list = super.getMapper().findMenuByRoleId(1);
        System.out.println(list);
    }
}
