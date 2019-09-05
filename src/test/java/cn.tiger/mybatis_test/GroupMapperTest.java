package cn.tiger.mybatis_test;

import cn.tiger.entity.GroupEntity;
import cn.tiger.mapper.GroupMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class GroupMapperTest extends BaseMapperTest<GroupMapper>{
    public GroupMapperTest() {
        super("mapper/GroupMapper.xml");
    }

    @Test
    public void addGroupTest(){
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setCreateUserId(2);
        groupEntity.setName("我的第一个分组");
        int i = super.getMapper().addGroup(groupEntity);
        System.out.println(i);
    }

    @Test
    public void deleteGroupByIdTest(){
        int i = super.getMapper().deleteGroupById(1);
        System.out.println(i);
    }

    @Test
    public void updateGroupTest(){
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setId(1);
        groupEntity.setName("我修改了组名");
        int i = super.getMapper().updateGroup(groupEntity);
        System.out.println(i);
    }

    @Test
    public void findGroupByUserIdTest(){
        List<GroupEntity> list = super.getMapper().findGroupByUserId(2);
        System.out.println(list);
    }
}
