package com.stu.user.dao;
import org.springframework.stereotype.Component;


import java.util.Map;
//@repository dao（实现dao访问）
 //@component (把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>）
@Component
public interface UserDao {
    Map<String,Object> adminLogin(String username, String password);
    Map<String,Object> studentLogin1(String username, String password);
    String getUsername(int stuId);
}
