package cn.tiger.mybatis_test;

import cn.tiger.entity.AccountEntity;
import cn.tiger.mapper.AccountMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AccountMapperTest extends BaseMapperTest<AccountMapper>{
    public AccountMapperTest() {
        super("mapper/AccountMapper.xml");
    }

    @Test
    public void addAccountTest(){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUserName("test1");
        accountEntity.setPassword("123456");
        int i = super.getMapper().addAccount(accountEntity);
        System.out.println(i);
    }

    @Test
    public void deleteAccountByIdTest(){
        int i = super.getMapper().deleteAccountById(2);
        System.out.println(i);
    }

    @Test
    public void updateAccountTest(){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(2);
        accountEntity.setUserName("test1-update");
        int i = super.getMapper().updateAccount(accountEntity);
        System.out.println(i);
    }

    @Test
    public void findByUsernameTest(){
        AccountEntity accountEntity = super.getMapper().findByUsername("test1-update");
        System.out.println(accountEntity);
    }

    @Test
    public void findByIdTest(){
        AccountEntity accountEntity = super.getMapper().findById(2);
        System.out.println(accountEntity);
    }
}
