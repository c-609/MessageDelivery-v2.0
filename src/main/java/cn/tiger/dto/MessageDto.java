package cn.tiger.dto;

import cn.tiger.entity.MessageEntity;
import lombok.Data;

/**
 * create by yifeng
 */
@Data
public class MessageDto extends MessageEntity {
    Integer deptIds;
}
