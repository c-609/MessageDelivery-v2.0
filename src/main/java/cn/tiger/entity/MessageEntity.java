package cn.tiger.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 消息实体
 */
@Data
@TableName("t_message")
public class MessageEntity extends Model<MessageEntity> {
    @TableId(type = IdType.AUTO)
    private Integer id; //消息id 自增
    @NotNull
    private Integer senderId; //发送者id
    private String senderName; //发送者名字
    @NotNull
    private String title; //标题
    @NotNull
    private String content; //内容
    private Date time; //发送时间
    private int number; //接受的总人数
    private int readNum; //已阅读的人数
    private int status;//消息状态 0：正常 1：逻辑删除
    @TableField(exist = false)
    private Integer deptRoleId;//部门与对应角色的组合id
    @TableField(exist = false)
    private IdentityEntity identity;
}
