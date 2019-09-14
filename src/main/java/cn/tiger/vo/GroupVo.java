package cn.tiger.vo;

import cn.tiger.entity.UserInfoEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 群组vo
 * create by yifeng
 */
@Data
public class GroupVo {
    private String groupName; // 组名称
    private Date createTime; // 创建时间
    private UserInfoEntity createUser; // 创建用户
    List<UserInfoEntity> userInfoEntityList; // 群组成员
}
