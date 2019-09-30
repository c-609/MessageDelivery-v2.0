package cn.tiger.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 群组
 */
@Data
@TableName("t_group")
@EqualsAndHashCode(callSuper = true)
public class GroupEntity extends Model<GroupEntity> {
    @TableId(type = IdType.AUTO)
    private Integer id; //id 自增
    @NotNull
    private String name; //群组名称
    @NotNull
    private Integer createUserId; //创建者id

    private Date createTime; //创建时间
    @Override
    protected Serializable pkVal() {
        return id;
    }
}
