package cn.tiger.service;

import cn.tiger.dto.UserDto;
import cn.tiger.entity.AccountEntity;
import cn.tiger.entity.IdentityEntity;
import cn.tiger.entity.UserInfoEntity;
import cn.tiger.mapper.AccountMapper;
import cn.tiger.mapper.UserInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户服务类
 * create by yifeng
 */
@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private UserDeptRoleService userDeptRoleService;

//    public Page getUserPage(Page page) {
//        return userInfoMapper.findAll(page);
//    }

    public PageInfo<UserInfoEntity> getUserPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<UserInfoEntity> pageInfo = new PageInfo<>(userInfoMapper.findAll());
        return pageInfo;
    }

    public UserInfoEntity getIdByUserInfo(Integer id) {
        if (id == null) {
            return null;
        }
        return userInfoMapper.findUserInfoById(id);
    }

    public List<UserInfoEntity> findUserListByDeptId(int deptId) {
        if (deptId < 0) {
            return null;
        }
        return userInfoMapper.findUserByDeptId(deptId);
    }

    public int addAccount(String username, String password) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUsername(username);
        accountEntity.setPassword(password);
        accountMapper.addAccount(accountEntity);
        return accountEntity.getId();
    }

    /**
     *在该方法中统一增加用户的所有信息
     * @param userDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(UserDto userDto) {
        int userId = addAccount(userDto.getUsername(), userDto.getPassword());
        userDto.setUserId(userId);
        userInfoMapper.updateUser(userDto);
        userDeptRoleService.addIdentityForUser(userId, userDto.getIdentityEntities().get(0).getId());
        return Boolean.TRUE;
    }

    /**
     * 删除用户
     * 1：删除用户-部门角色关系
     * 2：删除用户详情
     * 3：删除账号
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Integer userId) {
        userDeptRoleService.delIdentityForUser(userId);
        userInfoMapper.deleteUserById(userId);
        accountMapper.deleteAccountById(userId);
        return Boolean.TRUE;
    }

    /**
     * 保存用户信息
     * @param userDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean saveUser(UserDto userDto) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        AccountEntity accountEntity = new AccountEntity();
        BeanUtils.copyProperties(userDto, accountEntity);
        BeanUtils.copyProperties(userDto, userInfoEntity);
        accountEntity.setId(userDto.getUserId());
        userInfoMapper.updateUser(userInfoEntity);
        accountMapper.updateAccount(accountEntity);
        List<IdentityEntity> identityEntityList = userDto.getIdentityEntities();
        if (identityEntityList == null || identityEntityList.size() <= 0) {
            return Boolean.TRUE;
        }
        userDeptRoleService.delIdentityForUser(userDto.getUserId());
        for (int i = 0; i < identityEntityList.size(); i++) {
            IdentityEntity identityEntity = identityEntityList.get(i);
            userDeptRoleService.addIdentityForUser(userDto.getUserId(), identityEntity.getDeptRoleId());
        }

        return Boolean.TRUE;
    }

}
