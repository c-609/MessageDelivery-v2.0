package cn.tiger.common.core.util;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息主体
 * Builder 将set方法private话，并使用更优雅的其他方式设置set方法，
 * Accessors 驼峰法生产get和set
 * AllArgsConstructor 会生成一个包含所有变量的构造函数
 *
 * create by yifeng
 */
@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code = CommonConstants.SUCCESS;

    @Getter
    @Setter
    private String msg = "success";

    @Getter
    @Setter
    private T data;

    public R() {
        super();
    }

    public R(T data) {
        this.data = data;
    }

    public R(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public R(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = CommonConstants.FAIL;
    }
}
