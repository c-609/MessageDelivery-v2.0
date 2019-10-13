package cn.tiger.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 群通知实体
 */
@Data
@TableName("t_notification")
public class NoticeEntity extends Model<NoticeEntity> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 操作者id
     */
    @NotNull
    private Integer senderId;
    /**
     * 接收者id
     */
    private Integer getterId;

    /**
     * 提示信息
     */
    private String inviteReason;
    /**
     * 通知类型：
     *         1：邀请通知
     *         2：同意
     *         3：退出
     *         4:拒绝
     */
    @NotNull
    private Integer type;
    @NotNull
    private Integer groupId;
    @TableField(exist = false)
    private GroupEntity group;
    @TableField(exist = false)
    private UserInfoEntity sender;
    @TableField(exist = false)
    private UserInfoEntity getter;
    private LocalDateTime time;
}
