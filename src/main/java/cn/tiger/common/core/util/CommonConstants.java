package cn.tiger.common.core.util;

/**
 * create by yifeng
 */
public interface CommonConstants {

        /**
         * 删除
         */
        String STATUS_DEL = "1";
        /**
         * 正常
         */
        String STATUS_NORMAL = "0";

        /**
         * 锁定
         */
        String STATUS_LOCK = "102";

        /**
         * 菜单
         */
        String MENU = "0";

        /**
         * 编码
         */
        String UTF8 = "UTF-8";

        /**
         * JSON 资源
         */
        String CONTENT_TYPE = "application/json; charset=utf-8";

        /**
         * 前端工程名
         */
        String FRONT_END_PROJECT = "tiger-eye";

        /**
         * 后端工程名
         */
        String BACK_END_PROJECT = "tiger";

        /**
         * 成功标记
         */
        Integer SUCCESS = 0;
        /**
         * 失败标记
         */
        Integer FAIL = 1;


        /**
         * 用户或密码错误
         */
        Integer USERNAME_OR_PASSWORD_NON = 3;

        /**
         * 未登录
         */
        public final int NO_LOGIN = -1;

        /**
         * 未知错误
         */
        Integer UNKNOWN_EXCEPTION = -99;

        /**
         * 用户消息状态 未读
         */
        Integer USER_MESSAGE_STATUS_NOT_READER = 0;

        /**
         * 用户消息状态 已读
         */
        Integer USER_MESSAGE_STATUS_READER = 1;

        /**
         * 用户消息状态 删除
         */
        Integer USER_MESSAGE_STATUS_DEL = -1;

        /**
         * 消息状态 正常
         */
        Integer MESSAGE_STATUS_NORMAL = 0;

        /**
         * 消息状态 过期
         */
        Integer MESSAGE_STATUS_NOT_NORMAL = 1;

        /**
         * 部门类型 ：学生
         */
        Integer DEPT_TYPE_STUDENT = 1;
        /**
         * 部门类型 ：老师
         */
        Integer DEPT_TYPE_TEACHER = 2;


}
