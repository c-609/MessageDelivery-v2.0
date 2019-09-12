package cn.tiger.controller.manage;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.common.core.util.R;
import cn.tiger.entity.GroupEntity;
import cn.tiger.service.GroupService;
import cn.tiger.service.MessageUserService;
import cn.tiger.service.UserGroupService;
import cn.tiger.vo.GroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.acl.Group;
import java.util.Arrays;
import java.util.List;

/**
 * create by yifeng
 */
@RestController
@RequestMapping("/manage/group")
public class ManageGroupController {
    @Autowired
    private MessageUserService messageUserService;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private GroupService groupService;

    @RequestMapping("/create")
    public R create(@Validated GroupEntity groupEntity, Integer[] userIds) {
        boolean result = groupService.create(groupEntity, groupEntity.getCreateUserId(), userIds);
        if (!result)
            return R.builder().msg("创建失败").build();
        return R.builder().msg("创建成功").build();
    }

    /**
     * 根据群组id返回GroupVo
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        if (id == null || id.intValue() <= 0) {
            return R.builder().msg("未找到该群组").code(CommonConstants.PARAMETER_ERROR).build();
        }
        GroupVo groupVo = userGroupService.getGroupVoByGroupId(id);
        if (groupVo == null) {
            return R.builder().msg("未找到该群组").build();
        }
        return new R(groupVo);
    }

    /**
     * 增加组成员
     * @param groupId
     * @param userIds
     * @return
     */
    @RequestMapping("/add_member")
    public R addMemeber(Integer groupId, Integer[] userIds) {
        if (groupId == null || userIds == null || userIds.length <= 0) {
            return R.builder().msg("添加失败").code(CommonConstants.PARAMETER_ERROR).build();
        }
        int result = userGroupService.addUserToGroup(groupId, userIds);
        if (result <= 0) {
            return R.builder().msg("添加失败,请检查该组中是否存在用户").code(CommonConstants.UNKNOWN_EXCEPTION).build();
        }
        return R.builder().msg(new StringBuffer("成功添加" + result + "人").toString()).build();
    }

    @RequestMapping("/remove_member")
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

    @RequestMapping("/quit")
    public R quit(Integer groupId, Integer userId) {
        if (groupId == null || userId == null || userId <= 0) {
            return R.builder().msg("退出失败").code(CommonConstants.PARAMETER_ERROR).build();
        }
        int result = userGroupService.deleteUserToGroup(groupId, (Integer[]) Arrays.asList(userId).toArray());
        if (result <= 0) {
            return R.builder().msg("退出失败，请刷新").code(CommonConstants.UNKNOWN_EXCEPTION).build();
        }
        return R.builder().msg("退出成功").build();
    }

    @RequestMapping("get_join_group")
    public R getJoinedGroup(Integer userId) {
        List<GroupEntity> groupEntityList = messageUserService.findGroupByUserId(userId);
        if (groupEntityList == null || groupEntityList.size() <= 0) {
            return R.builder().msg("没有加入任何群组").build();
        }
        return new R(groupEntityList);
    }
}
