package cn.tiger.mybatis_test;

import cn.tiger.entity.GroupEntity;
import cn.tiger.entity.UserInfoEntity;
import cn.tiger.mapper.UserGroupMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class UserGroupMapperTest extends BaseMapperTest<UserGroupMapper>{
    public UserGroupMapperTest() {
        super("mapper/UserGroupMapper.xml");
    }

    @Test
    public void addUserToGroupTest(){
        Integer[] userIds = new Integer[]{2,10};
        int i = super.getMapper().addUserToGroup(1,userIds);
        System.out.println(i);
    }

    @Test
    public void removeUserTest(){
        Integer[] userIds = new Integer[]{2,10};
        int i = super.getMapper().removeUser(1,userIds);
        System.out.println(i);
    }

    @Test
    public void findUserByGroupIdTest(){
        List<UserInfoEntity> list = super.getMapper().findUserByGroupId(1);
        System.out.println(list);
    }

    @Test
    public void findGroupByUserIdTest(){
        List<GroupEntity> list = super.getMapper().findGroupByUserId(2);
        System.out.println(list);
    }
}
