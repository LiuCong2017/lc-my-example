package lc.springsecuritydb.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {
    // 如果你的密码是用MD5进行处理的，那么在这里你需要对明文rawPassword做处理后返回
    @Override
    public String encode(CharSequence rawPassword) {
//        这里实现的PasswordEncoder 没有对密码做任何处理（也就是明文）
        return rawPassword.toString() ;
    }
//    验证密码
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword) ;
    }
}
