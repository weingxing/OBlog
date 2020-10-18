package com.oxygen.oblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * SpringSecurity配置类，控制访问权限、设置密码编码器
 * @author Oxygen
 * @since 2020/09/27
 */
@Configuration
@EnableWebSecurity
public class SecurityConﬁg extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MyPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http){
        try {
            http.headers().frameOptions().disable();

            http.authorizeRequests()
                    .antMatchers("/favicon.ico",
                            "/lib/**", "/css/**", "/js/**", "/images/**", "/404.html", "/502.html",
                            "/", "/home", "/detail.html",
                            "/login", "/register",
                            "/user/testUsername", "/user/addUser",
                            "/comment/getCommentByPage", "/comment/getPublicComment",
                            "/comment/addComment", "/comment/getCommentByCoid",
                            "/content/getPublicByPage", "/content/getContentByCid",
                            "/archives", "/about", "/category",
                            "/options/getSite", "/options/getDescription", "/options/getKeywords",
                            "/options/getMusic", "/getMusicUrl").permitAll()
                    .antMatchers("/user_welcome", "/admin",
                            "/user/updatePassword", "/user/updateUser",
                            "/comment/getMyComment", "/comment/wantDelete",
                            "/content/getMyContentByPage", "/content/addContent",
                            "/content/wantDelete", "/getMenu",
                            "/meta/getAll", "/options/getAllOptions", "/options/getTitle",
                            "/options/getSite", "/options/getDescription", "/options/getKeywords",
                            "/options/getMusic", "/page/user/**",
                            "/page/user-setting.html", "/page/user-password.html")
                    .hasAnyAuthority("default","admin") // 用户可访问
                    .antMatchers("/**").hasAuthority("admin")  // 管理员可访问全部内容
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")  // 指定登录页面
                    .permitAll()
                    .successForwardUrl("/login?status=successful") // 登陆成功跳转
                    .failureForwardUrl("/login?status=error")  // 登陆失败跳转
                    .and()
                    .logout().logoutUrl("/logout").logoutSuccessUrl("/")
                    .permitAll()
                    .and()
                    .csrf()
                    .disable();  // 关闭跨域
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
