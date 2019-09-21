package cn.tiger.service;

import cn.tiger.entity.NoticeEntity;
import cn.tiger.mapper.NoticeMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 群通知服务类
 * create by yifeng
 */
@Service
public class NoticeService {

    /**
     * 增加群通知
     * @param noticeEntity 群消息通知实体
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addNotice(NoticeEntity noticeEntity, Integer getterId) {
        noticeEntity.setGetterId(getterId);
        noticeEntity.insert();
        return Boolean.TRUE;
    }

    /**
     * 批量插入
     * @param noticeEntity 群消息通知实体
     * @param getterIds 接受者的用户唯一标识
     * @return
     */
    public boolean bulkInsert(NoticeEntity noticeEntity, Integer[] getterIds) {
        Arrays.stream(getterIds).forEach(getterId -> {
            addNotice(noticeEntity, getterId);
        });
        return Boolean.TRUE;
    }

    /**
     * 获取所有通知列表
     * @param senderId 通过发送者的用户唯一标识
     * @return
     */
    public List<NoticeEntity> findBySenderId(Integer senderId) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setSenderId(senderId);
        List<NoticeEntity> result = noticeEntity.selectList(Wrappers.lambdaQuery(noticeEntity));
        if (result == null || result.size() <= 0) {
            return null;
        }
        return result;
    }


}
