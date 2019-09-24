package cn.tiger.dto;

import cn.tiger.entity.UserInfoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * create by yifeng
 */
@Data
public class UserDto extends UserInfoEntity {
    private String username; //用户名
    private String password; //密码
}
