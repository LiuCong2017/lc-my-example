//package com.example.demo.filter;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.annotation.Resource;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 自定义过滤器
// */
//@Component
//public class AutoAuthenticationFilter extends OncePerRequestFilter {
//
//    @Resource
//    private AuthenticationManager authenticationManager;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("admin","123");
//        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        Authentication auth = authenticationManager.authenticate(authenticationToken);
//        if (auth!=null){
//            SecurityContextHolder.getContext().setAuthentication(auth);
//            request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext()) ;
//        }
//        System.out.println("--------------------------Auto Authenticaton Filter...") ;
//        filterChain.doFilter(request, response) ;
//    }
//
//}
