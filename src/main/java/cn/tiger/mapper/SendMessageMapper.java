package cn.tiger.mapper;

import cn.tiger.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SendMessageMapper {
    //发送消息
    int sendMessage(@Param("messageId") Integer messageId ,@Param("userIds") Integer[] userIds);
    //通过messageid删除
    int deleteSendMsgByMsgId(@Param("messageId") Integer messageId);
    //通过用户id和消息id删除消息
    int deleteSendMsgByUserId(@Param("messageId") Integer messageId,@Param("userId") Integer userId);
    //当用户已读消息时，修改消息状态
    int updateSendMsgStatus(@Param("messageId") Integer messageId,@Param("userId") Integer UserId
                                ,@Param("status") int status);
    //通过消息id查看未读消息的用户
    List<UserInfoEntity> findNotReadUserByMsgId(@Param("messageId") Integer messageId);
    //查看已读消息的用户
    List<UserInfoEntity> findReadUserByMsgId(@Param("messageId") Integer messageId);
}
