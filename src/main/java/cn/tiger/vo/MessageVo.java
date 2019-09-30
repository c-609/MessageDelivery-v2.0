package cn.tiger.vo;

import cn.tiger.entity.IdentityEntity;
import cn.tiger.entity.MessageEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * create by yifeng
 */
@Data
public class MessageVo extends MessageEntity {
    private Integer top;
}
