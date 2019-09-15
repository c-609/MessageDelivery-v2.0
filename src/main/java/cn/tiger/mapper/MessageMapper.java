package cn.tiger.mapper;

import cn.tiger.entity.MessageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper extends BaseMapper<MessageEntity> {
    //添加消息
    int addMessage(MessageEntity message);
    //删除消息
    int deleteMessageById(@Param("id") Integer id);
    //不能修改消息，删除，重发

    //修改消息的读取数
    int updateMessageReadNum(@Param("messageId") Integer messageId);

    //通过发送人的id查看消息
    List<MessageEntity> findMessageBySenderId(@Param("senderId") Integer senderId);

}
