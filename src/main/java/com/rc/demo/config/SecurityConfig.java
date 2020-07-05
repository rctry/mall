package com.rc.demo.config;

import com.rc.demo.componet.JwtAuthenticationTokenFilter;
import com.rc.demo.componet.RestAuthenticationEntryPoint;
import com.rc.demo.componet.RestAccessDeniedHandler;
import com.rc.demo.dto.AdminUserDetails;
import com.rc.demo.mbg.model.UmsAdmin;
import com.rc.demo.mbg.model.UmsPermission;
import com.rc.demo.service.IUmsAdminService;
import com.rc.demo.service.impl.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {

public class SecurityConfig{

    @Autowired
    private IUmsAdminService adminService;
//    @Autowired
//    private RestAccessDeniedHandler restAccessDeniedHandler;
//    @Autowired
//    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
//    @Autowired
//    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/")
//                .permitAll();
//        http.csrf()
//                .disable();
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,
//                        "/",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/swagger-resources/**",
//                        "/v2/api-docs/**"
//                )
//                .permitAll()
//                .antMatchers("/admin/login", "/admin/register")
//                .permitAll()
//                .antMatchers(HttpMethod.OPTIONS)
//                .permitAll()
//                .anyRequest()
//                .authenticated();
//        http.headers().cacheControl();
//        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//        http.exceptionHandling()
//                .accessDeniedHandler(restAccessDeniedHandler)
//                .authenticationEntryPoint(restAuthenticationEntryPoint);
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService())
//                .passwordEncoder(passwordEncoder());
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            UmsAdmin admin = adminService.getAdminByUsername(username);
            if (admin != null) {
                List<UmsPermission> permissionList = adminService.getPermissionList(admin.getId());
                return new AdminUserDetails(admin,permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

}
