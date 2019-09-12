package cn.tiger.service;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.entity.GroupEntity;
import cn.tiger.mapper.UserGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户-群组服务类
 * create by yifeng
 */
@Service
public class UserGroupService {
    @Autowired
    private UserGroupMapper userGroupMapper;

    @Transactional(rollbackFor = Exception.class)
    public int addUserToGroup(int groupId, Integer[] userIds){
        return userGroupMapper.addUserToGroup(groupId, userIds);
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteUserToGroup(int groupId, Integer[] userIds) {
        return userGroupMapper.removeUser(groupId, userIds);
    }


//    public GroupEntity
}
