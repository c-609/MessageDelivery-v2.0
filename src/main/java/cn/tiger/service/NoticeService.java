package cn.tiger.service;

import cn.tiger.entity.GroupEntity;
import cn.tiger.entity.NoticeEntity;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        noticeEntity.setTime(LocalDateTime.now());
        Arrays.stream(getterIds).forEach(getterId -> {addNotice(noticeEntity, getterId);});
        return Boolean.TRUE;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean update(NoticeEntity noticeEntity) {
        noticeEntity.updateById();
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
        // 填充群组给前端使用
        GroupEntity groupEntity = new GroupEntity();
        for (NoticeEntity entity : result) {
            groupEntity.setId(entity.getGroupId());
            entity.setGroup(groupEntity.selectById());
        }
        if (result == null || result.size() <= 0) {
            return null;
        }
        return result;
    }

    /**
     * 获取所有通知列表
     * @param getterId 通过接收者的用户唯一标识
     * @return
     */
    public List<NoticeEntity> findByGetterId(Integer getterId, Integer type) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setGetterId(getterId);
        noticeEntity.setType(type);
        List<NoticeEntity> result = noticeEntity.selectList(Wrappers.lambdaQuery(noticeEntity));
        // 填充群组给前端使用
        GroupEntity groupEntity = new GroupEntity();
        for (NoticeEntity entity : result) {
            groupEntity.setId(entity.getGroupId());
            entity.setGroup(groupEntity.selectById());
        }
        if (result == null || result.size() <= 0) {
            return null;
        }
        return result;
    }

    /**
     * 过滤 TODO 使用双层循环过滤掉用户本地中已经存在的群通知，后期或许有更好的算法实现
     * @param noticeEntityList
     * @param existedNoticeIds 需要过滤的群通知id
     * @return
     */
    public List<NoticeEntity> filterExisted(List<NoticeEntity> noticeEntityList, Integer[] existedNoticeIds) {
        List<NoticeEntity> result = new ArrayList<>(noticeEntityList.size());
        for (int i = 0; i < noticeEntityList.size(); i++) {
            boolean whetherToAdd = true; // 是否添加标识
            for (int j = 0; j < existedNoticeIds.length; j++) {
                if (noticeEntityList.get(i).getId().equals(existedNoticeIds[j])) { // 若本地列表中已经存在，则不添加
                    whetherToAdd = false;
                }
            }
            if (whetherToAdd) {
                result.add(noticeEntityList.get(i));
            }
        }
        return result;
    }
}
