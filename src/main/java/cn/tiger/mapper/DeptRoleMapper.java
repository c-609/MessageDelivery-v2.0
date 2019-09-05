package cn.tiger.mapper;

import cn.tiger.entity.DeptEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptRoleMapper {
    //添加部门与角色对应关系
    //可同时为一个部门添加多个角色对应关系
    int addDeptRole(@Param("deptId") Integer deptId,@Param("roleIds") Integer[] roleIds);

    //删除部门与角色对应关系
    int deleteDeptRoleByDeptId(@Param("deptId") Integer deptId);

    //修改的话 是先删除，再添加

    //查看所有的部门角色对应关系
    List<DeptEntity> findAll();
}
