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
     * @param messageId 消息唯一标识
     * @param userId 用户唯一标识
     * @param status 用户消息状态
     * @return 1：参数错误 0：正常
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateMessageUserStatus(Integer messageId, Integer userId, int status) {
        if (messageId == null || userId == null || status < 0) {
            return CommonConstants.SUCCESS;
        }

        sendMessageMapper.updateSendMsgStatus(messageId, userId, status);
        return CommonConstants.FAIL;
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


}
