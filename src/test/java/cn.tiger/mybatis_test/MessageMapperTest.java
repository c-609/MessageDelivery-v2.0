package cn.tiger.mybatis_test;

import cn.tiger.entity.MessageEntity;
import cn.tiger.mapper.MessageMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class MessageMapperTest extends BaseMapperTest<MessageMapper>{
    public MessageMapperTest() {
        super("mapper/MessageMapper.xml");
    }

   @Test
    public void addMessageTest(){
       MessageEntity messageEntity = new MessageEntity();
       messageEntity.setSenderId(2);
       messageEntity.setSenderName("张三");
       messageEntity.setTitle("测试一");
       messageEntity.setContent("你好，这是测试一");
       messageEntity.setNumber(20);
       messageEntity.setDeptRoleId(1);
       int i = super.getMapper().addMessage(messageEntity);
       System.out.println(i);
   }

   @Test
    public void deleteMessageByIdTest(){
        int i = super.getMapper().deleteMessageById(1);
       System.out.println(i);
   }

   @Test
   public void updateMessageReadNumTest(){
        int i = super.getMapper().updateMessageReadNum(1);
       System.out.println(i);
   }

    @Test
    public void findMessageBySenderIdTest(){
        List<MessageEntity> list = super.getMapper().findMessageBySenderId(2);
        System.out.println(list);
    }
}
