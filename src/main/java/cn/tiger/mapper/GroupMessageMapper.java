package cn.tiger.mapper;

import cn.tiger.entity.MessageEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMessageMapper {
    //给群组发消息
    int sendMsgToGroup(@Param("messageId") Integer messageId,@Param("groupIds") Integer[] groupIds);

    int deleteMsgInGroup(@Param("messageIds") Integer[] messageIds,@Param("groupId") Integer groupId);

    //通过组id查询该组所发的消息
    List<MessageEntity> findMessageByGroupId(@Param("groupId") Integer groupId);

}
