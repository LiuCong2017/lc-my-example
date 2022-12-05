package com.example.demo.config;

import com.example.demo.entiry.ErrorEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Resource
//    private AutoAuthenticationFilter authFilter;

    //自定义配置
    @SuppressWarnings("deprecation")
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        // 配置用户名密码角色，及密码编码方式
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("guest").password("123456").roles("ADMIN") // 配置guest用户，该用户拥有ADMIN角色
                .and()
                .withUser("test").password("test").roles("USERS"); //配置一个用于USERS权限的用户
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // 将自定义的过滤器添加到UsernamePasswordAuthenticationFilter过滤器的前面
//        httpSecurity.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class) ;

        // 关闭csrf，就是删除CsrfFilter过滤器。
        httpSecurity.csrf().disable();
        // 拦截任意请求
//        httpSecurity.authorizeRequests().anyRequest().authenticated();

//        httpSecurity
//                .authorizeRequests() // 获取基于SpEL表达式的基于URL的授权对象
//                .antMatchers("/resources/**") // 设定URI路径规则以/resoures开头的任意请求
//                .permitAll(); // 只要是基于上面/resources开头的请求都进行放行 (只放行指定的资源 - 方向/resource请求的资源 )

        httpSecurity.authorizeRequests().antMatchers("/resources/**", "/cache/**").permitAll() ;

        // 这里无需使用ROLE_前缀，系统会自动插入该前缀
        httpSecurity.authorizeRequests().antMatchers("/demos/**").hasRole("USERS"); // /demos/**必须具备USERS角色
        httpSecurity.authorizeRequests().antMatchers("/api/**").hasRole("ADMIN"); // /api/**必须具备ADMIN角色
//        //配置多权限
//        httpSecurity.authorizeRequests().antMatchers("/demos/**").hasAnyRole("USERS", "AKKF", "BLLE") ;
//        httpSecurity.authorizeRequests().antMatchers("/api/**").hasAnyRole("ADMIN", "MGR", "SYSTEM") ;
//        //多个URI具有相同的权限
//        httpSecurity.authorizeRequests().antMatchers("/demos/**", "/api/**").hasAnyAuthority("ROLE_USERS","ROLE_ADMIN");
//        //对请求的Method控制
//        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET).permitAll();


//        httpSecurity
//                .authorizeRequests()
//                        .anyRequest() // 任意请求
//                                .authenticated(); // 必须进行登录认证授权

        // 如果请求不是上面配置的访问uri前缀则进行登录(这里需要加上该句，否则不会出现登录页面)
        httpSecurity.formLogin()
                //通过自定义的方式展示错误信息 - 错误的用户名密码
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        response.setContentType("application/json;charset=UTF-8");
                        PrintWriter out = response.getWriter();
                        out.println(new ErrorEntity(-1,exception.getMessage()));
                        out.close();
                    }
                })
                //自定义配置登录页 (登录页面指向配置的Controller即可)
                .loginPage("/custom/login");

        // 自定义访问拒绝页面 - 无权限的异常
//        httpSecurity.exceptionHandling().accessDeniedPage("/access/denied");
        httpSecurity.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println(new ErrorEntity(-2,accessDeniedException.getMessage()));
                out.close();
            }
        });

    }

}
