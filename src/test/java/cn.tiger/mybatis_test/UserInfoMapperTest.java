package cn.tiger.mybatis_test;

import cn.tiger.entity.IdentityEntity;
import cn.tiger.entity.UserInfoEntity;
import cn.tiger.mapper.UserInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class UserInfoMapperTest extends BaseMapperTest<UserInfoMapper>{
    public UserInfoMapperTest() {
        super("mapper/UserInfoMapper.xml");
    }
   @Test
    public void deleteUserByIdTest(){
        int i = super.getMapper().deleteUserById(10);
       System.out.println(i);
   }

   @Test
    public void updateUserTest(){
       UserInfoEntity userInfoEntity = new UserInfoEntity();
       userInfoEntity.setUserId(2);
       userInfoEntity.setAge(16);
       userInfoEntity.setName("myName");
       int i = super.getMapper().updateUser(userInfoEntity);
       System.out.println(i);
   }
    @Test
    public void findUserInfoByIdTest(){
        UserInfoEntity userInfoEntity = super.getMapper().findUserInfoById(2);

        System.out.println(userInfoEntity);
    }

    @Test
    public void findUserInfoByNameTest(){
        List<UserInfoEntity> list = super.getMapper().findUserInfoByName("my");
        System.out.println(list);
    }
//    @Test
//    public void findAll(){
//        List<UserInfoEntity> list = super.getMapper().findAll();
//        System.out.println(list);
//    }

    @Test
    public void findUserByDeptIdTest(){
        List<UserInfoEntity> list = super.getMapper().findUserByDeptId(1);
        System.out.println(list);
    }
}
