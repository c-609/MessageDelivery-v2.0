package cn.tiger.service;

import cn.tiger.MessageDeliveryApplicationTest;
import cn.tiger.entity.UserInfoEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends MessageDeliveryApplicationTest {
	@Autowired
    UserService userService;
	
    @Test
    public void getIdByUserInfo() {
        UserInfoEntity userInfoEntity = userService.getIdByUserInfo(1);
        System.out.println(userInfoEntity);
    }
}
