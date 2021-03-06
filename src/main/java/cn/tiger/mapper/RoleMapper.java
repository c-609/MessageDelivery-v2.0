package cn.tiger.mapper;

import cn.tiger.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<RoleEntity> {
    //添加角色
    int addRole(RoleEntity role);
    //删除角色
    int deleteRoleById(@Param("id") Integer id);
    //修改角色信息
    int updateRole(RoleEntity role);
    //查找所有
    Page<List<RoleEntity>> findAll(Page page);
    //通过用户id查找角色
    List<RoleEntity> findRoleByUserId(@Param("userId") Integer userId);
    //通过部门id查找部门下的角色
    List<RoleEntity> findRoleByDeptId(@Param("deptId") Integer deptId);


}
