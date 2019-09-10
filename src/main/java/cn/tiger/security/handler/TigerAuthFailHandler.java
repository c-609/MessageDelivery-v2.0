package cn.tiger.security.handler;

import cn.tiger.common.core.util.CommonConstants;
import cn.tiger.common.core.util.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录失败处理
 * create by yifeng
 */
@Component
public class TigerAuthFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse resp, AuthenticationException e) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setStatus(401);
        R resultBean = new R();
        resultBean.setCode(CommonConstants.FAIL);
        if (e instanceof BadCredentialsException ||
                e instanceof UsernameNotFoundException) {
            resultBean.setCode(CommonConstants.USERNAME_OR_PASSWORD_NON);
            resultBean.setMsg("用户名或密码错误");
        }
        else if (e instanceof LockedException) {
            resultBean.setCode(Integer.getInteger(CommonConstants.STATUS_LOCK));
            resultBean.setMsg("账户被锁定，请联系管理员!");
        }
//                        else if (e instanceof CredentialsExpiredException) {
//                            respBean = RespBean.error("密码过期，请联系管理员!");
//                        } else if (e instanceof AccountExpiredException) {
//                            respBean = RespBean.error("账户过期，请联系管理员!");
//                        } else if (e instanceof DisabledException) {
//                            respBean = RespBean.error("账户被禁用，请联系管理员!");
//                        }
        else {
            resultBean.setCode(CommonConstants.UNKNOWN_EXCEPTION);
            resultBean.setMsg("未知异常");
        }
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        out.write(om.writeValueAsString(resultBean));
        out.flush();
        out.close();
    }
}
