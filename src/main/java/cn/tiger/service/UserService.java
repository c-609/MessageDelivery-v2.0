package cn.tiger.service;

import cn.hutool.system.UserInfo;
import cn.tiger.entity.UserInfoEntity;
import cn.tiger.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by yifeng
 */
@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfoEntity getIdByUserInfo(Integer id) {
        if (id == null) {
            return null;
        }
        return userInfoMapper.findUserInfoById(id);
    }
}
