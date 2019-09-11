package cn.tiger.security.sevice;

import cn.tiger.entity.AccountEntity;
import cn.tiger.entity.IdentityEntity;
import cn.tiger.entity.UserInfoEntity;
import cn.tiger.mapper.AccountMapper;
import cn.tiger.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * create by yifeng
 */
@Service
public class TigerUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity accountEntity= accountMapper.findByUsername(username);
        if (accountEntity == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserInfoEntity userInfoEntity = userInfoMapper.findUserInfoById(accountEntity.getId());

        return getUserDetails(userInfoEntity, accountEntity);
    }

    /**
     * 构建userdetails
     * @return 用户信息
     */
    private UserDetails getUserDetails(UserInfoEntity userInfo, AccountEntity accountEntity) {
        List<IdentityEntity> identityEntityList = userInfo.getIdentityEntities();
        List<String> roleList = new ArrayList<>(identityEntityList.size());
        identityEntityList.forEach(identity -> {roleList.add(identity.getRoleId().toString());});
        // 此处放入roleIds，参照pig,在SecurityUtils中取出
        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(roleList.toArray(new String[0]));

        // 构造security用户
        return new TigerUser(userInfo.getUserId(), identityEntityList, accountEntity.getUserName(), accountEntity.getPassword(),
                true, true, true, true, authorities);
    }

}
