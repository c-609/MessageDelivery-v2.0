package cn.tiger.mybatis_test;

import cn.tiger.entity.MessageEntity;
import cn.tiger.mapper.GroupMessageMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class GroupMessageMapperTest extends BaseMapperTest<GroupMessageMapper>{
    public GroupMessageMapperTest() {
        super("mapper/GroupMessageMapper.xml");
    }

    @Test
    public void sendMsgToGroupTest(){
        Integer[] groupIds = new Integer[]{1,2};
        int i = super.getMapper().sendMsgToGroup(1,groupIds);
        System.out.println(i);
    }
    @Test
    public void deleteMsgInGroupTest(){
        Integer[] messageIds = new Integer[]{1};
        int i = super.getMapper().deleteMsgInGroup(messageIds,1);
        System.out.println(i);
    }

    @Test
    public void findMessageByGroupIdTest(){
        List<MessageEntity> list = super.getMapper().findMessageByGroupId(1);
        System.out.println(list);
    }
}
