package cn.tiger.mapper;

import cn.tiger.entity.MenuEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {
    //添加菜单
    int addMenu(MenuEntity menu);
    //删除菜单
    int deleteMenuById(@Param("id") Integer id);
    //修改菜单
    int updateMenu(MenuEntity menu);
    //查询所有菜单
    List<MenuEntity> findAll();
    //通过用户id查菜单
    List<MenuEntity> findMenuByUserId(@Param("userId") Integer userId);
    //通过角色id查菜单
    List<MenuEntity> findMenuByRoleId(@Param("roleId") Integer roleId);
}
