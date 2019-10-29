package cn.tiger.controller.msg;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.common.core.util.R;
import cn.tiger.entity.NoticeEntity;
import cn.tiger.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/sender")
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

    /**
     * 获取群通知，通过接受者id和类型
     * @param getterId
     * @param type 类型 1：邀请通知   2：同意 3：退出 4:拒绝
     * @return
     */
    @GetMapping("/getter")
    public R getByGetterId(Integer getterId, Integer type, Integer[] existedNoticeIds) {
        if (getterId == null || getterId.intValue() <= 0) {
            return R.builder().msg("未找到群通知").code(CommonConstants.PARAMETER_ERROR).build();
        }
        List<NoticeEntity> noticeEntityList = noticeService.findByGetterId(getterId, type);
        if (existedNoticeIds != null && existedNoticeIds.length > 0) {
            noticeEntityList = noticeService.filterExisted(noticeEntityList, existedNoticeIds);
        }
        if (noticeEntityList == null || noticeEntityList.size() <= 0) {
            return R.builder().msg("没有需要的群通知").build();
        }
        return new R(noticeEntityList);
    }

    /**
     * 发送群通知
     * @param noticeEntity
     * @param getterIds 接受者id
     * @return
     */
    @PostMapping
    public R sendNotice(NoticeEntity noticeEntity, Integer[] getterIds) {
        if (getterIds == null || getterIds.length <= 0) {
            return R.builder().msg("没有接收人员").code(CommonConstants.PARAMETER_ERROR).build();
        }

        noticeService.bulkInsert(noticeEntity, getterIds);
        return R.builder().msg("发送成功").build();
    }

    /**
     * 修改群通知,修改群通知类型
     * @param id
     * @param type 类型 1：邀请通知   2：同意 3：退出 4:拒绝
     * @param getter_id 接收着唯一标识号
     * @return
     */
    @PutMapping
    public R updateNotice(Integer id, Integer type, Integer getter_id) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setId(id);
        noticeEntity.setType(type);
        noticeEntity.setGetterId(getter_id);
        noticeService.update(noticeEntity);
        return new R();
    }

}
