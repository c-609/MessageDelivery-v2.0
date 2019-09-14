package cn.tiger.vo;

import cn.tiger.entity.IdentityEntity;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * create by yifeng
 */
public class MessageVo {
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
    private IdentityEntity identity;
}
