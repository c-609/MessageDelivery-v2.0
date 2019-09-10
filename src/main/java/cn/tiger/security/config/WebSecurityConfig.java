package cn.tiger.security.config;

import cn.tiger.security.handler.TigerAuthFailHandler;
import cn.tiger.security.handler.TigerAuthSuccessHandler;
import cn.tiger.security.handler.TigerLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * 安全配置类
 * create by yifeng
 */
//@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TigerAuthSuccessHandler tigerAuthSuccessHandler;
    @Autowired
    private TigerAuthFailHandler tigerAuthFailHandler;
    @Autowired
    private TigerLogoutHandler tigerLogoutHandler;
    @Autowired
    private CustomMetadataSource metadataSource;
    @Autowired
    private UrlAccessDecisionManager urlAccessDecisionManager;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**","/login_p");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(metadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                .formLogin().loginPage("/login_p")
                // 自定义登录请求，对应表单中的action
                // 可在postman中发送 post请求 http://localhost:8088/login?username=asdf123&password=123456
                .loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .successHandler(tigerAuthSuccessHandler)
                .permitAll()
                .failureHandler(tigerAuthFailHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(tigerLogoutHandler)
                .permitAll()
                .and().csrf().disable();
    }

}
