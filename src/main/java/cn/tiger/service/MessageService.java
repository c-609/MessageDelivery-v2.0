package cn.tiger.service;

import cn.tiger.entity.DeptEntity;
import cn.tiger.entity.MessageEntity;
import cn.tiger.entity.UserInfoEntity;
import cn.tiger.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 消息服务类
 * create by yifeng
 */
@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private MessageUserService messageUserService;
    @Autowired
    private UserService userService;

    /**
     * 发送消息
     * @param messageEntity
     * @param userIds
     * @param userDeptRoleId 用户身份关联唯一标识号
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean send(MessageEntity messageEntity, Integer[] userIds, Integer userDeptRoleId) {
        if (userIds == null || userIds.length <= 0 || userDeptRoleId == null || userDeptRoleId.intValue() <= 0) {
            return Boolean.FALSE;
        }
        // 设置发送人数
        messageEntity.setNumber(userIds.length);
        // 设置发送人名字
        if (messageEntity.getSenderName() == null || messageEntity.getSenderName().equals("")) {
            UserInfoEntity userInfoEntity = userService.getIdByUserInfo(messageEntity.getSenderId());
            messageEntity.setSenderName(userInfoEntity.getName());
        }
        messageEntity.setTime(new Date());
        messageEntity.setDeptRoleId(userDeptRoleId); // 设置发送身份
        // 增加消息, message.id会自动填充到messageEntity中
        messageMapper.addMessage(messageEntity);
        // 增加消息用户关系
        messageUserService.addMessageUser(messageEntity.getId(), userIds);
        return Boolean.TRUE;
    }

    /**
     *
     * @param messageEntity
     * @param userInfoEntityList 用户详情对象
     * @return
     */
    public boolean send(MessageEntity messageEntity, List<UserInfoEntity> userInfoEntityList, Integer userDeptRoleId) {
        Integer[] userIds = new Integer[userInfoEntityList.size()];
        for (int i = 0; i < userInfoEntityList.size(); i++) {
            userIds[i] = userInfoEntityList.get(i).getUserId();
        }
        return send(messageEntity, userIds, userDeptRoleId);
    }

    /**
     *
     * @param userId 发送者唯一标识号
     * @return
     */
    public List<MessageEntity> findSendMessageById(Integer userId) {
        return messageMapper.findMessageBySenderId(userId);
    }

    /**
     * // TODO 使用双层循环过滤掉用户本地中已经存在的消息，后期或许有更好的算法实现
     * @param messageEntityList
     * @param existedMessageIds 本地已经存在的消息列表
     * @return
     */
    public List<MessageEntity> filterExisted(List<MessageEntity> messageEntityList, Integer[] existedMessageIds) {
        List<MessageEntity> result = new ArrayList<>(messageEntityList.size());
        for (int i = 0; i < messageEntityList.size(); i++) {
            boolean whetherToAdd = true; // 是否添加标识
            for (int j = 0; j < existedMessageIds.length; j++) {
                if (messageEntityList.get(i).getId().equals(existedMessageIds[j])) { // 若本地列表中已经存在，则不添加
                    whetherToAdd = false;
                }
            }
            if (whetherToAdd) {
                result.add(messageEntityList.get(i));
            }
        }
        return result;
    }


}
