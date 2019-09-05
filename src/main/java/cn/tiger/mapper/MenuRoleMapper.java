package cn.tiger.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRoleMapper {
    //为角色添加权限
    int addAuthorizationForRole(@Param("roleId") Integer roleId,@Param("menuIds") Integer[] menuIds);

    //通过角色id删除角色的权限
    int deleteAuthorizationById(@Param("roleId") Integer roleId);

    //修改： 先删除，再添加
}
