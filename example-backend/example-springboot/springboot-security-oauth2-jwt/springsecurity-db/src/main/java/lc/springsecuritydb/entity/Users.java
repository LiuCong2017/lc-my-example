package lc.springsecuritydb.entity;


import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "t_users")
public class Users implements UserDetails {
    @Id
    private String id ;
    private String username ;
    private String password ;
    @Column(columnDefinition = "int default 1")
    private Integer enabled = 1;
    @Column(columnDefinition = "int default 0")
    private Integer locked = 0 ;
//    private Collection<GrantedAuthority> authorities = new ArrayList<>() ;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}