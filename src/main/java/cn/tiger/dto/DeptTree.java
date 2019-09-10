package cn.tiger.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门树
 * create by yifeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends TreeNode {
    private String name;
}
