package cn.tiger.controller.msg;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.common.core.util.R;
import cn.tiger.entity.MessageEntity;
import cn.tiger.entity.UserInfoEntity;
import cn.tiger.mapper.SendMessageMapper;
import cn.tiger.service.MessageService;
import cn.tiger.service.MessageUserService;
import cn.tiger.vo.MessageVo;
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
     * 0 : 未读 1：已读 2：本地删除
     * @param messageId
     * @param userId
     * @return
     */
    @PostMapping("/edit_state_delete")
    public R editStateDelete(Integer messageId, Integer userId) {
        if (!messageUserService.hasBeenRead(messageId, userId)) {
        return R.builder().msg("请先读取该条消息").build();
        }
        int result = messageUserService.updateMessageUserStatus(messageId, userId, CommonConstants.USER_MESSAGE_STATUS_DEL);
        if (result == CommonConstants.FAIL) {
            return R.builder().msg("参数错误").code(CommonConstants.PARAMETER_ERROR).build();
        }
        return R.builder().msg("删除成功，可在回收站中查找").build();
    }

    /**
     * 编辑消息状态为已读
     * @param messageId
     * @param userIds 用户唯一标识数组
     * @return
     */
    @PostMapping("/edit_state_read")
    public R editStateRead(Integer messageId, Integer[] userIds) {
        int result = CommonConstants.FAIL;
        for (Integer userId : userIds) {
            int mark = messageUserService.updateMessageUserStatusToRead(messageId, userId);
            if (CommonConstants.FAIL.equals(result)) { // 只要有一个用户的未读状态标记为已读成功 即可返回成功
                result = mark;
            }
        }

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
        List<MessageVo> messageVoList = messageService.conversionToVo(messageEntityList, userId);
        if (messageVoList == null || messageVoList.size() <= 0) {
            return R.builder().msg("没有未读消息").build();
        }
        return new R(messageVoList);
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

    /**
     * 设置置顶状态
     * @param messageId 消息唯一标识
     * @return
     */
    @PutMapping("/top")
    public R getNotReadUser(Integer messageId, Integer userId, Integer top) {
        if (messageId == null || userId == null || top == null) {
            return R.builder().msg("参数错误").code(CommonConstants.PARAMETER_ERROR).build();
        }
        messageUserService.setTopState(messageId, userId, top);
        return new R();
    }


}
