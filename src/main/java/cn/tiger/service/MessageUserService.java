package cn.tiger.service;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.entity.GroupEntity;
import cn.tiger.entity.MessageEntity;
import cn.tiger.entity.UserInfoEntity;
import cn.tiger.mapper.SendMessageMapper;
import cn.tiger.mapper.UserGroupMapper;
import cn.tiger.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户消息服务类
 * create by yifeng
 */
@Service
public class MessageUserService {
    @Autowired
    private SendMessageMapper sendMessageMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;

    /**
     * 更新用户消息的状态
     * 不能发使用此方法设置消息为已读状态，使用以下方法即可
     *      updateMessageUserStatusToRead()
     * @param messageId 消息唯一标识
     * @param userId 用户唯一标识
     * @param status 用户消息状态
     * @return 1：参数错误 0：正常
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateMessageUserStatus(Integer messageId, Integer userId, int status) {
        if (messageId == null || userId == null || status < 0) {
            return CommonConstants.FAIL;
        }
        sendMessageMapper.updateSendMsgStatus(messageId, userId, status);
        return CommonConstants.SUCCESS;
    }

    /**
     * 更新用户消息状态为已读，由于涉及到+1操作，所以需要加锁
     * @param messageId
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public synchronized int updateMessageUserStatusToRead(Integer messageId, Integer userId) {
        // 取出用户消息对应的状态
        Integer status = sendMessageMapper.findStatusByMessageUser(userId, messageId);
        if (status == null) {
            return CommonConstants.FAIL;
        }
        // 如果已读，则返回成功即可
        if (status.intValue() == CommonConstants.USER_MESSAGE_STATUS_READER) {
            return CommonConstants.SUCCESS;
        }

        MessageEntity messageEntity = new MessageEntity();
        messageEntity = messageEntity.selectById(messageId);
        // 如果已经读满了，则直接返回
        if (messageEntity.getNumber() == messageEntity.getReadNum()) {
            return CommonConstants.SUCCESS;
        }
        // 此处需先把user_message 状态查出来，在更新，更新完后消息才能加一操作
        sendMessageMapper.updateSendMsgStatus(messageId, userId, CommonConstants.USER_MESSAGE_STATUS_READER);

        if (messageEntity.getNumber() > messageEntity.getReadNum()) {
            messageEntity.setReadNum(messageEntity.getReadNum() + 1);
            messageEntity.updateById();
        }

        return CommonConstants.SUCCESS;


    }

    /**
     * 获取群组列表 通过用户id
     * @param userId 用户唯一标识
     * @return
     */
    public List<GroupEntity> findGroupByUserId(Integer userId) {
        if (userId == null) {
            return null;
        }
        return userGroupMapper.findGroupByUserId(userId);
    }

    /**
     * 增加 用户消息关系
     * @param messageId
     * @param userIds
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int addMessageUser(Integer messageId, Integer[] userIds) {
        int result = sendMessageMapper.sendMessage(messageId, userIds);
        return result;
    }

    /**
     * 获取未读消息
     * @param userId 用户唯一标识
     * @return
     */
    public List<MessageEntity> getNewArrivalMessage(Integer userId) {
        if (userId.intValue() <= 0) {
            return null;
        }
        return sendMessageMapper.findNotReadMessageByUserId(userId);
    }

//    public List<MessageVo> conversionEntityToVo(List<MessageEntity> messageEntitieList) {
//        List<MessageVo> messageVoList = new ArrayList<>(messageEntitieList.size());
//        messageEntitieList.forEach(messageEntity -> {
//
//        });
//    }

    /**
     * 获取未读消息的用户信息
     * @param messageId
     * @return
     */
    public List<UserInfoEntity> findNotReadUser(Integer messageId) {
        if (messageId.intValue() <= 0) {
            return null;
        }
        return sendMessageMapper.findNotReadUserByMsgId(messageId);
    }

    /**
     * 是否已读
     * @param messageId 消息唯一标识
     * @param getterId 接受者唯一标识
     * @return
     */
    public boolean hasBeenRead(Integer messageId, Integer getterId) {
        Integer status = sendMessageMapper.findStatusByMessageUser(getterId, messageId);
        if (status.equals(CommonConstants.USER_MESSAGE_STATUS_READER)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public int setTopState(Integer messageId, Integer getterId, Integer top) {
        return sendMessageMapper.updateIsTop(messageId, getterId, top);
    }


}
