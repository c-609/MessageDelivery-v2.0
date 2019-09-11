package cn.tiger.service;

import cn.tiger.mapper.SendMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户消息服务类
 * create by yifeng
 */
@Service
public class MessageUserService {
    @Autowired
    private SendMessageMapper sendMessageMapper;

    /**
     * 更新用户消息的状态
     * @param messageId 消息唯一标识
     * @param userId 用户唯一标识
     * @param status 用户消息状态
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateMessageUserStatus(Integer messageId, Integer userId, int status) {
        if (messageId == null || userId == null || status < 0) {
            return 1;
        }

        sendMessageMapper.updateSendMsgStatus(messageId, userId, status);
        return 0;
    }
    


}
