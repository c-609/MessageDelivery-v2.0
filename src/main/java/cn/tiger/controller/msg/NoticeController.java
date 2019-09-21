package cn.tiger.controller.msg;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.common.core.util.R;
import cn.tiger.entity.NoticeEntity;
import cn.tiger.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 群通知控制类
 * 用户拉人-> 向用户发送通知，用户同意或者不同意
 * create by yifeng
 */
@RestController
@RequestMapping("/msg/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 通过发送者id获取群通知
     * @param senderId
     * @return
     */
    @GetMapping("/get_sender")
    public R getBySenderId(Integer senderId) {
        if (senderId == null || senderId.intValue() <= 0) {
            return R.builder().msg("未找到群通知").code(CommonConstants.PARAMETER_ERROR).build();
        }
        List<NoticeEntity> result = noticeService.findBySenderId(senderId);
        if (result == null) {
            return R.builder().msg("未找到群通知").build();
        }
        return new R(result);
    }

}
