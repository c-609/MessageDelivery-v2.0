package cn.tiger.mapper;

import cn.tiger.entity.IdentityEntity;
import cn.tiger.entity.UserDeptRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDeptRoleMapper extends BaseMapper<UserDeptRoleEntity> {


    /**
     *   给用户添加部门和角色
     * @param userId 用户id
     * @param identityId 身份id ，部门与角色对用关系的组合id
     * @return
     */
    int addIdentityForUser(@Param("userId") Integer userId,@Param("identityId") Integer identityId);

    /**
     * 删除用户身份
     * @param id 用户部门角色关联表的id
     * @return
     */
    int deleteIdentityById(@Param("id") Integer id);

    /**
     * 删除用户身份
     * @param userId 用户部门角色关联表的用户唯一标识
     * @return
     */
    int deleteIdentityByUserId(@Param("userId") Integer userId);


    /**
     *  修改用户身份
     * @param id  用户部门角色关联表的d
     * @param identityId 身份id
     * @return
     */
    int updateIdentity(@Param("id") Integer id,@Param("identityId") Integer identityId);

    /**
     * 通过用户id 查身份（部门）
     * @param userId
     * @return
     */
    List<IdentityEntity> findIdentityByUserId(@Param("userId") Integer userId);

}
