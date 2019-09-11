package cn.tiger.mapper;

import cn.tiger.entity.GroupEntity;
import cn.tiger.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupMapper {
    //向群组里加人
    int addUserToGroup(@Param("groupId") Integer groupId,@Param("userIds") Integer[] userIds);
    //移除群组里面的人
    int removeUser(@Param("groupId") Integer groupId,@Param("userIds") Integer[] userIds);
    //通过组id查询所有的组员
    List<UserInfoEntity> findUserByGroupId(@Param("groupId") Integer groupId);

    //通过用户id查该用户所在的群组
    List<GroupEntity> findGroupByUserId(@Param("userId") Integer userId);
}
