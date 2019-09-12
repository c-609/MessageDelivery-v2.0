package cn.tiger.controller.manage;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.common.core.util.R;
import cn.tiger.entity.DeptEntity;
import cn.tiger.service.DeptService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.invoke.util.Wrapper;

import java.util.List;

/**
 * create by yifeng
 */
@RestController
@RequestMapping("/manage/dept")
public class ManageDeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 获取树的根节点
     * @return
     */
    @RequestMapping("/tree/root")
    public R findTreeRoot() {
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setParentId(-1);
        // 默认
        return new R(deptEntity.selectOne(Wrappers.lambdaQuery()));
    }

    /**
     * 获取用户的树状组织机构列表
     * @param userId
     * @return
     */
    @GetMapping("/get_user_organization")
    @ResponseBody
    public R getUserOrganization(Integer userId) {
        if (userId == null || userId.intValue() <= 0) {
            return R.builder().msg("参数不正确").code(CommonConstants.PARAMETER_ERROR).build();
        }
        return new R(deptService.findTreeByUserDept(userId));
    }

    /**
     * 获取部门的子节点
     * @param id
     * @return
     */
    @GetMapping("/child/{id}")
    public R getChild(@PathVariable Integer id) {
        if (id == null || id.intValue() <= 0) {
            return R.builder().msg("参数不正确").code(CommonConstants.PARAMETER_ERROR).build();
        }
        List<DeptEntity> deptEntityList = deptService.findChilderNode(id);
        if (deptEntityList == null || deptEntityList.size() <= 0) {
            return R.builder().msg("无子节点").build();
        }
        return new R(deptService.findChilderNode(id));
    }

}
