package cn.tiger.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 群组
 */
@Data
public class GroupEntity {
    private Integer id; //id 自增
    @NotNull
    private String name; //群组名称
    @NotNull
    private Integer createUserId; //创建者id
    @NotNull
    private Date createTime; //创建时间
}
