package lc.springsecuritydb.controller;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/business")
public class BussinessController {
//    @GetMapping("/{id}")
//    public Object get(@PathVariable("id") Integer id) {
//        return "receive - " + id ;
//    }

    @GetMapping("/{id}")
    @RolesAllowed("ROLE_USERS") // ①接收一个String[] 数组，可以定义多个角色
    @Secured("ROLE_USERS1") // ②接收一个String[] 数组，可以定义多个角色。
    @PreAuthorize("hasRole('USERS')") // ③可以使用SpEL表达式, 该注解用于指定方法访问控制表达式的注释，该表达式将被计算以确定是否允许方法调用
    public Object get(@PathVariable("id") Integer id) {
        return "receive - " + id ;
    }

}
