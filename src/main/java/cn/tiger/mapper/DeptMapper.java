package cn.tiger.mapper;

import cn.tiger.entity.DeptEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptMapper {
    //添加部门
    int addDept(DeptEntity dept);
    //通过id删除部门
    int deleteDeptById(@Param("id") Integer id);
    //修改部门
    int updateDept(DeptEntity dept);
    //查寻所有部门
    List<DeptEntity> findAll();
    //通过id查部门
    DeptEntity findDeptById(@Param("id") Integer id);
    //通过部门名称查部门(模糊查)
    List<DeptEntity> findDeptByName(@Param("name") String name);

    //通过部门id获取下一层子部门
    List<DeptEntity> findDeptByDeptParentId(@Param("parentId") Integer parentId);

}
