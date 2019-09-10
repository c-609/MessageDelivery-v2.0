package cn.tiger.security.util;

import cn.tiger.security.sevice.TigerUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * 安全工具类
 *
 * create by yifeng
 */
@UtilityClass
public class SecurityUtils {

    /**
     * 获取Authentication
     * @return
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     */
    public TigerUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof TigerUser) {
            return (TigerUser) principal;
        }
        return null;
    }

    /**
     * 获取用户
     */
    public TigerUser getUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }

        return getUser(authentication);
    }

    /**
     * 获取用户角色信息
     *
     * @return 角色信息
     */
    public List<Integer> getRoles() {
        // TODO  此处取出角色信息 ids
        return null;
    }

}
