package cn.tiger.security.handler;

import cn.tiger.common.core.util.R;
import cn.tiger.security.sevice.TigerUser;
import cn.tiger.security.util.SecurityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录成功处理
 * create by yifeng
 */
@Component
public class TigerAuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        R resultBean = new R<TigerUser>(SecurityUtils.getUser(authentication));
        resultBean.setCode(0);
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        out.write(om.writeValueAsString(resultBean));
        out.flush();
        out.close();
    }
}
