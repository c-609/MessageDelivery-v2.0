package cn.tiger.entity;

import lombok.Data;

import java.util.Date;

/**
 * 群通知实体
 */
@Data
public class NoticeEntity {
    private Integer id;
    private Integer senderId;
    private UserInfoEntity sender;
    private Integer getterId;
    private UserInfoEntity getter;
    private int type;
    private Integer groupId;
    private GroupEntity group;
    private Date time;
}
