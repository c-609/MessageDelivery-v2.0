package cn.tiger.service;

import cn.tiger.entity.GroupEntity;
import cn.tiger.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * create by yifeng
 */
@Service
public class GroupService {
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private UserGroupService userGroupService;

    /**
     * TODO 暫時使用发送用户id的方式，优化点
     * @param groupEntity
     * @param createUid
     * @param uids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean create(GroupEntity groupEntity,Integer createUid, Integer[] uids) {
        groupEntity.setCreateTime(new Date());
        int groupId = groupMapper.addGroup(groupEntity); // 返回刚刚创建的组id
        if (groupId <= 0) {
            return Boolean.TRUE;
        }
        int row = userGroupService.addUserToGroup(groupId, uids);
        return Boolean.TRUE;
    }

}
