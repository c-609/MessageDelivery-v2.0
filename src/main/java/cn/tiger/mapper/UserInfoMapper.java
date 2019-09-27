package cn.tiger.mapper;

import cn.tiger.entity.UserInfoEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper {
    //添加userinfo   数据库触发器 添加用户的id 和 name ，name 开始 为 账号
//    int addUserInfo();

    //通过id删除
    int deleteUserById(@Param("id") Integer id);

    //修改用户信息
    int updateUser(UserInfoEntity userInfoEntity);

    //通过id查找用户信息
    UserInfoEntity findUserInfoById(@Param("id") Integer id);

    //通过用户名查找User 模糊查询
    List<UserInfoEntity> findUserInfoByName(@Param("name") String name);

    //查找所有
    Page<List<UserInfoEntity>> findAll(Page page);

//    List<UserInfoEntity> findAll();
    /**
     * 通过部门id查用户
     * @param deptId 部门id
     * @return
     */
    List<UserInfoEntity> findUserByDeptId(@Param("deptId") Integer deptId);

}
