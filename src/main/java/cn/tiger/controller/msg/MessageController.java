package cn.tiger.controller.msg;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.common.core.util.R;
import cn.tiger.service.MessageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通知控制类
 * create by yifeng
 */
@Controller
@RequestMapping("/msg")
public class MessageController {
    @Autowired
    private MessageUserService messageUserService;

    /**
     * 编辑状态为本地删除删除
     * 0 : 未读 1：已读 2：本地删除
     * @param message
     * @param userId
     * @return
     */
    @RequestMapping("/edit_State_delete")
    @ResponseBody
    public R editStateDelete(Integer message, Integer userId) {
        int result = messageUserService.updateMessageUserStatus(message, userId, CommonConstants.USER_MESSAGE_STATUS_DEL);
        if (result == CommonConstants.FAIL) {
            return R.builder().msg("参数错误").code(CommonConstants.PARAMETER_ERROR).build();
        }
        return R.builder().msg("删除成功").build();
    }

}
