package cn.tiger.mybatis_test;

import cn.tiger.entity.MessageEntity;
import cn.tiger.entity.UserInfoEntity;
import cn.tiger.mapper.SendMessageMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class SendMessageMapperTest extends BaseMapperTest<SendMessageMapper>{
    public SendMessageMapperTest() {
        super("mapper/SendMessageMapper.xml");
    }

    @Test
    public void sendMessageTest(){
        Integer[] userIds = new Integer[]{2,10};
        int i = super.getMapper().sendMessage(1,userIds);
       System.out.println(i);
    }
    //TODO
    @Test
    public void deleteSendMsgByMsgIdTest(){
        int i = super.getMapper().deleteSendMsgByMsgId(1);
        System.out.println(i);
    }
    @Test
    public void deletesendMsgByUserIdTest(){
        int i = super.getMapper().deleteSendMsgByUserId(1,2);
        System.out.println(i);
    }

    @Test
    public void updateSendMsgStatusTest(){
        int i = super.getMapper().updateSendMsgStatus(1,2,1);
        System.out.println(i);
    }

    @Test
    public void findNotReadUserByMsgIdTest(){
        List<UserInfoEntity> list = super.getMapper().findNotReadUserByMsgId(1);
        System.out.println(list);
    }

    @Test
    public void findReadUserByMsgIdTest(){
        List<UserInfoEntity> list = super.getMapper().findReadUserByMsgId(1);
        System.out.println(list);
    }

    @Test
    public void findNotReadMessageByUserIdTest(){
        List<MessageEntity> list = super.getMapper().findNotReadMessageByUserId(2);
        System.out.println(list);
    }

    @Test
    public void findMessageByUserIdTest(){
        List<MessageEntity> list = super.getMapper().findMessageByUserId(10);
        System.out.println(list);
    }
}
