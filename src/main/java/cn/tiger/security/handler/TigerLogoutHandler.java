package cn.tiger.security.handler;

import cn.tiger.common.core.util.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 注销处理
 * create by yifeng
 */
@Component
public class TigerLogoutHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        out.write(om.writeValueAsString(new R<String>("注销成功")));
        out.flush();
        out.close();
    }
}
