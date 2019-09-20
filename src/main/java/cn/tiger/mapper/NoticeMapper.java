package cn.tiger.mapper;

import cn.tiger.entity.NoticeEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeMapper {
    //添加群通知
    int addGroupNotification(NoticeEntity noticeEntity);

    //通过id删除群通知
    int deleteGroupNotificationById(@Param("id") Integer id);

    //要修改吗？

    //通过id查群消息
    NoticeEntity findById(@Param("id") Integer id);

    //通过发送者id查群通知
    List<NoticeEntity> findBySenderId(@Param("senderId") Integer senderId);

    //通过接收者id 和type 类型查群通知
    List<NoticeEntity> findByGetterIdAndType(@Param("getterId") Integer getterId, @Param("type") int type);


}
