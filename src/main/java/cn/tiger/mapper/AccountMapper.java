package cn.tiger.mapper;

import cn.tiger.entity.AccountEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {
    //注册账号
    int addAccount(AccountEntity accountEntity);
    //通过id删除
    int deleteAccountById(@Param("id") Integer id);
    //修改账号表
    int updateAccount(AccountEntity accountEntity);
    //通过username查
    AccountEntity findByUsername(@Param("username") String username);
    //通过id查
    AccountEntity findById(@Param("id") Integer id);

}
