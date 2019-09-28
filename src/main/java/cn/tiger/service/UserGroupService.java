package cn.tiger.service;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.entity.GroupEntity;
import cn.tiger.entity.UserInfoEntity;
import cn.tiger.mapper.GroupMapper;
import cn.tiger.mapper.UserGroupMapper;
import cn.tiger.vo.GroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户-群组服务类
 * create by yifeng
 */
@Service
public class UserGroupService {
    @Autowired
    private UserGroupMapper userGroupMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupMapper groupMapper;

    /**
     * 在群组中添加成员
     * @param groupId
     * @param userIds
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int addUserToGroup(int groupId, Integer[] userIds){
        List<UserInfoEntity> userInfoEntityList = userGroupMapper.findUserByGroupId(groupId);
        // 如果该群组中没人，则直接添加
        if (userInfoEntityList == null || userInfoEntityList.size() <= 0) {
            return userGroupMapper.addUserToGroup(groupId, userIds);
        }
        // 否则需要排除掉userIds中在群组中已经存在的用户
        Set<Integer> userSet = new HashSet();
        boolean mark = true;
        int result = 0;
        for (int i = 0; i < userIds.length; i++) {
            for (UserInfoEntity userInfoEntity : userInfoEntityList) {
                if (userInfoEntity.getUserId().equals(userIds[i])) {
                    mark = false;
                    break;
                }
            }
            if (mark)
                userSet.add(userIds[i]);
            mark = true; // 重新初始化标记
        }
        if (userSet.size() <= 0) {
            return 0;
        }
        return userGroupMapper.addUserToGroup(groupId, (Integer[]) userSet.toArray(new Integer[0]));
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteUserToGroup(int groupId, Integer[] userIds) {
        return userGroupMapper.removeUser(groupId, userIds);
    }


    public List<UserInfoEntity> findUserListByGroup(Integer groupId) {
        if (groupId != null)
            return userGroupMapper.findUserByGroupId(groupId);
        return null;
    }

    /**
     * 通过创建者id获取组列表
     * @param userId 用户唯一标识
     * @return
     */
    public List<GroupEntity> findGroupByCreatorGroupUser(Integer userId) {
        if (userId == null || userId.intValue() <= 0) {
            return null;
        }
        return groupMapper.findGroupByUserId(userId);
    }

    public List<UserInfoEntity> findUserListByGroupId(Integer groupId) {
        if (groupId == null || groupId.intValue() <= 0) {
            return null;
        }
        return userGroupMapper.findUserByGroupId(groupId);
    }

    /**
     * 通过组id获取该组的Vo
     * @param groupId
     */
    public GroupVo getGroupVoByGroupId(Integer groupId) {
        if (groupId == null || groupId.intValue() <= 0) {
            return null;
        }
        GroupVo groupVo = new GroupVo();
        GroupEntity groupEntity = new GroupEntity();
        GroupEntity groupEntity1 = groupEntity.selectById(groupId);
        groupVo.setUserInfoEntityList(userGroupMapper.findUserByGroupId(groupId));
        groupVo.setCreateUser(userService.getIdByUserInfo(groupEntity1.getCreateUserId()));
        groupVo.setCreateTime(groupEntity1.getCreateTime());
        groupVo.setGroupName(groupEntity1.getName());
        return groupVo;
    }
}
