package cn.tiger.entity;

import lombok.Data;

import java.util.Date;

/**
 * 群组
 */
@Data
public class GroupEntity {
    private Integer id; //id 自增
    private String name; //群组名称
    private Integer createUserId; //创建者id
    private Date createTime; //创建时间
}
