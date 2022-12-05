package lc.springsecuritydb.service;


import lc.springsecuritydb.dao.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Resource
    private UsersRepository usersRepository ;
//    通过用户名查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username) ;
    }
}
