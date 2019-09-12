package cn.tiger.service;

import cn.tiger.MessageDeliveryApplicationTest;
import cn.tiger.entity.UserInfoEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * create by yifeng
 */
public class UserGroupServiceTest extends MessageDeliveryApplicationTest {

    @Autowired
    private UserGroupService userGroupService;

    @Test
    public void findUserByGroupId() {
        List<UserInfoEntity> userInfoEntityList = userGroupService.findUserListByGroup(1);
        System.out.println(userInfoEntityList);
    }

    @Test
    public void testGetGroupVoByGroupId() {
        System.out.println(userGroupService.getGroupVoByGroupId(2));
    }

}
