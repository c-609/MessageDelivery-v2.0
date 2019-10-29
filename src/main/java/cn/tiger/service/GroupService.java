package cn.tiger.service;

import cn.tiger.entity.GroupEntity;
import cn.tiger.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
     * @param uids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer create(GroupEntity groupEntity, Integer[] uids) {
        // 过滤掉创建者id
        List<Integer> uidsList = Arrays.stream(uids).filter(id -> id != groupEntity.getCreateUserId()).collect(Collectors.toList());
        groupEntity.setCreateTime(new Date());
        groupMapper.addGroup(groupEntity); // 刚刚创建的组id将会填充到groupEntity中
        int groupId = groupEntity.getId();
        if (groupId <= 0) {
            return null;
        }
        if (uids != null && uids.length > 0){
            userGroupService.addUserToGroup(groupId, uidsList.toArray(new Integer[0]));
        }
        return groupId;
    }

    public boolean delete(Integer groupId) {
//        userGroupService
        return Boolean.TRUE;
    }

}
