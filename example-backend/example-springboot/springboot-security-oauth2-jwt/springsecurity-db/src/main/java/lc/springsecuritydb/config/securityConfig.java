package lc.springsecuritydb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * jsr250Enabled：启用对JSR-250注释的支持。@RolesAllowed
 * prePostEnabled：启用基于表达式的语法支持（jsr250Enabled和securedEnabled都是基于简单角色的约束）。@PreAuthorize
 * securedEnabled：启用@Secured注解的支持。
 */
@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true,  prePostEnabled = true, securedEnabled = true)
public class securityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider() ;
        authenticationProvider.setUserDetailsService(userDetailsService) ;
        authenticationProvider.setPasswordEncoder(passwordEncoder) ;
        return authenticationProvider ;
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable() ;
//        http.authorizeRequests().antMatchers("/resources/**", "/cache/**", "/process/login").permitAll() ;
//        http.authorizeRequests().antMatchers("/demos/**").hasRole("USERS") ;
//        http.authorizeRequests().antMatchers("/api/**").hasRole("ADMIN") ;
//        // 上面的配置都是基于之前的文章，这里我们不需要关心，仅仅看下面这个接口配置接口
//        // 这里我们会要求所有以/business开始的所有请求
//        http.authorizeRequests().antMatchers("/business/**").authenticated() ;
//    }

}
