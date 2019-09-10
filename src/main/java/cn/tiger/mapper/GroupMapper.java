package cn.tiger.mapper;

import cn.tiger.entity.GroupEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMapper {
    //创建组,返回创建的组id
    int addGroup(GroupEntity groupEntity);

    //删除组
    int deleteGroupById(@Param("id") Integer id);

    //修改组
    int updateGroup(GroupEntity groupEntity);

    //通过创建者id查询所有组
    List<GroupEntity> findGroupByUserId(@Param("userId") Integer userId);
}
