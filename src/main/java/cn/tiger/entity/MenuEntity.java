package cn.tiger.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 菜单实体
 */
@Data
public class MenuEntity {
    private Integer id; //菜单id
    private String name; //名称
    private String path; //路径
    private String permission; //菜单权限标识
    private Integer parentId; //父节点id
    private String icon; //图标
    private String component; //前端VUE界面
    private int sort; //排序值
    private int keepAlive;//0：开启 1：关闭
    private int type; //类型 0：菜单 1：按钮
    private int delFlag; //逻辑删除标记 0：正常 1 删除
    private Date updateTime; //最近一次更新时间
    private Integer operatorId; //操作人员id
    private Date createTime; //创建时间
    private List<RoleEntity> roles;
}
