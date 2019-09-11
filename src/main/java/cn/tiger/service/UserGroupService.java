package cn.tiger.service;

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
        if (userIds == null ||userIds.length <= 0 || groupId == 0) {
            return 0;
        }
        return userGroupMapper.addUserToGroup(groupId, userIds);
    }

//    public GroupEntity
}
