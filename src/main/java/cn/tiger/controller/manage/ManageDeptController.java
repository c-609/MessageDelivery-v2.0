package cn.tiger.controller.manage;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.common.core.util.R;
import cn.tiger.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * create by yifeng
 */
@Controller
@RequestMapping("/manage/dept")
public class ManageDeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/get_user_organization")
    @ResponseBody
    public R getUserOrganization(Integer userId) {
        if (userId == null || userId.intValue() <= 0) {
            return R.builder().msg("参数不正确").code(CommonConstants.PARAMETER_ERROR).build();
        }
        return new R(deptService.findTreeByUserDept(userId));
    }
}
