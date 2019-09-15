package cn.tiger.controller.msg;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.common.core.util.R;
import cn.tiger.entity.MessageEntity;
import cn.tiger.entity.UserInfoEntity;
import cn.tiger.service.MessageService;
import cn.tiger.service.MessageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 通知控制类
 * create by yifeng
 */
@RestController
@RequestMapping("/msg")
public class MessageController {
    @Autowired
    private MessageUserService messageUserService;
    @Autowired
    private MessageService messageService;
    /**
     * 编辑状态为本地删除删除
     * TODO 如果该用户  消息未读，则编辑后该消息的人数是否需要+1
     * 0 : 未读 1：已读 2：本地删除
     * @param messageId
     * @param userId
     * @return
     */
    @PostMapping("/edit_state_delete")
    public R editStateDelete(Integer messageId, Integer userId) {
        // TODO 此处在就改之前建议先检查该条消息是否已读，虽然前端已经控制了，但是在后端应该在控制一下
        int result = messageUserService.updateMessageUserStatus(messageId, userId, CommonConstants.USER_MESSAGE_STATUS_DEL);
        if (result == CommonConstants.FAIL) {
            return R.builder().msg("参数错误").code(CommonConstants.PARAMETER_ERROR).build();
        }
        return R.builder().msg("删除成功，可在回收站中查找").build();
    }

    /**
     * 编辑消息状态为已读
     * @param messageId
     * @param userId
     * @return
     */
    @PostMapping("/edit_state_read")
    public R editStateRead(Integer messageId, Integer userId) {
        int result = messageUserService.updateMessageUserStatusToRead(messageId, userId);
        if (result == CommonConstants.FAIL) {
            return R.builder().msg("参数错误").code(CommonConstants.PARAMETER_ERROR).build();
        }
        return new R();
    }

    /**
     * 获取未读消息
     * @param userId
     * @param existedMessageIds 在前端本地中已经存在的未读消息，该消息不必再发一次
     * @return
     */
    @RequestMapping("/not_read")
    public R getNotReadMessageByUsreId(Integer userId, Integer[] existedMessageIds) {
        if (userId == null) {
            return R.builder().msg("参数错误").code(CommonConstants.PARAMETER_ERROR).build();
        }
        List<MessageEntity> messageEntityList = messageUserService.getNewArrivalMessage(userId);

        // 过滤
        if (existedMessageIds != null && existedMessageIds.length > 0) {
            messageEntityList = messageService.filterExisted(messageEntityList, existedMessageIds);
        }
        if (messageEntityList == null || messageEntityList.size() <= 0) {
            return R.builder().msg("没有未读消息").build();
        }
        return new R(messageEntityList);
    }

    /**
     * 获取已发出的消息
     * @param userId
     * @return
     */
    @RequestMapping("/send_out_message")
    public R getSendOutMessage(Integer userId, Integer[] existedMessageIds) {
        if (userId == null) {
            return R.builder().msg("参数错误").code(CommonConstants.PARAMETER_ERROR).build();
        }
        List<MessageEntity> messageEntityList = messageService.findSendMessageById(userId);
        // 过滤
        if (existedMessageIds != null && existedMessageIds.length > 0) {
            messageEntityList = messageService.filterExisted(messageEntityList, existedMessageIds);
        }
        if (messageEntityList == null || messageEntityList.size() <= 0) {
            return R.builder().msg("没有发出的消息").build();
        }
        return new R(messageEntityList);
    }

    /**
     * 获取未读用户列表
     * @param messageId 消息唯一标识
     * @return
     */
    @GetMapping("/not_read_user")
    public R getNotReadUser(Integer messageId) {
        if (messageId == null) {
            return R.builder().msg("参数错误").code(CommonConstants.PARAMETER_ERROR).build();
        }
        List<UserInfoEntity> userInfoEntityList =messageUserService.findNotReadUser(messageId);
        if (userInfoEntityList == null || userInfoEntityList.size() <= 0) {
            return R.builder().msg("没有未读人员，请刷新").build();
        }
        return new R(userInfoEntityList);
    }




}
