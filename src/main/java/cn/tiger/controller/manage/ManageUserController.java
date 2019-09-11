package cn.tiger.controller.manage;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.common.core.util.R;
import cn.tiger.entity.UserInfoEntity;
import cn.tiger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户控制类
 * create by yifeng
 */
@Controller
@RequestMapping("/manage/user/")
public class ManageUserController {
    @Autowired
    private UserService userService;

    /**
     * 获取用户详情
     * @param userId 用户唯一标识
     * @return
     */
    @GetMapping("/get_user_information")
    @ResponseBody
    public R getUserInfo(Integer userId) {
        if (userId == null) {
            return R.builder().msg("用户id不能为空").code(CommonConstants.PARAMETER_ERROR).build();
        }
        UserInfoEntity userInfoEntity = userService.getIdByUserInfo(userId);
        if (userInfoEntity == null) {
            return R.builder().msg("该用户不存在").build();
        }
        return new R(userInfoEntity);
    }



}
