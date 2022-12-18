package lc.springsecuritydb.dao;


import lc.springsecuritydb.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
    Users findByUsername(String username) ;
}
