package cn.tiger.controller.msg.process;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.common.core.util.R;
import cn.tiger.entity.MessageEntity;
import cn.tiger.mapper.UserDeptRoleMapper;
import cn.tiger.service.MessageService;
import cn.tiger.service.UserDeptRoleService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接发消息控制类
 * create by yifeng
 */
@RestController
@RequestMapping("/msg/process")
public class ProcessController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserDeptRoleService userDeptRoleService;
    /**
     * 发送消息
     * @param messageEntity
     * @param userIds
     * @param roleId 发送者选择的角色
     * @param deptId 发送者选择的部门
     * @return
     */
    @PostMapping("/send")
    public R send(@Validated MessageEntity messageEntity, Integer[] userIds, Integer roleId, Integer deptId) {
        if (userIds == null || userIds.length <= 0 || roleId == null || deptId == null) {
            return R.builder().msg("数据不正确，发送失败").code(CommonConstants.PARAMETER_ERROR).build();
        }
        // 获取用户选择的身份关联id
        int userDeptRoleId = userDeptRoleService.findUserDeptRole(deptId, roleId, messageEntity.getSenderId());
        if (userDeptRoleId <= 0) {
            return R.builder().msg("请选择正确的身份").code(CommonConstants.PARAMETER_ERROR).build();
        }
        boolean result = messageService.send(messageEntity, userIds, userDeptRoleId);
        if (!result) {
            return R.builder().msg("发送失败").build();
        }
            return R.builder().msg("发送成功").build();
    }


}
