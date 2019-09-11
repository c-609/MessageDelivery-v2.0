package cn.tiger.controller.manage;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.common.core.util.R;
import cn.tiger.entity.GroupEntity;
import cn.tiger.service.GroupService;
import cn.tiger.service.MessageUserService;
import cn.tiger.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * create by yifeng
 */
@Controller
@RequestMapping("/manage/group")
public class ManageGroupController {
    @Autowired
    private MessageUserService messageUserService;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private GroupService groupService;

    @RequestMapping("/create")
    @ResponseBody
    public R create(@Validated GroupEntity groupEntity, Integer[] userIds) {
        boolean result = groupService.create(groupEntity, groupEntity.getCreateUserId(), userIds);
        if (!result)
            return R.builder().msg("创建失败").build();
        return R.builder().msg("创建成功").build();
    }

    /**
     * 增加组成员
     * @param groupId
     * @param userIds
     * @return
     */
    @RequestMapping("/add_member")
    @ResponseBody
    public R addMemeber(Integer groupId, Integer[] userIds) {
        if (groupId == null || userIds == null || userIds.length <= 0) {
            return R.builder().msg("添加失败").code(CommonConstants.PARAMETER_ERROR).build();
        }
        int result = userGroupService.addUserToGroup(groupId, userIds);
        if (result <= 0) {
            return R.builder().msg("添加失败").code(CommonConstants.UNKNOWN_EXCEPTION).build();
        }
        return R.builder().msg(new StringBuffer("成功添加" + result + "人").toString()).build();
    }

    @RequestMapping("/remove_member")
    @ResponseBody
    public R removeMember(Integer groupId, Integer[] userIds) {
        if (groupId == null || userIds == null || userIds.length <= 0) {
            return R.builder().msg("移除失败").code(CommonConstants.PARAMETER_ERROR).build();
        }
        int result = userGroupService.deleteUserToGroup(groupId, userIds);
        if (result <= 0) {
            return R.builder().msg("移除失败，请刷新").code(CommonConstants.UNKNOWN_EXCEPTION).build();
        }
        return R.builder().msg(new StringBuffer("成功移除" + result + "人").toString()).build();
    }

    @RequestMapping("get_join_group")
    @ResponseBody
    public R getJoinedGroup(Integer userId) {
        List<GroupEntity> groupEntityList = messageUserService.findGroupByUserId(userId);
        if (groupEntityList == null || groupEntityList.size() <= 0) {
            return R.builder().msg("没有加入任何群组").build();
        }
        return new R(groupEntityList);
    }
}
