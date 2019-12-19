package com.stu.user.service;
import com.stu.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Service//（注入dao）
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    /**
     * 用户登陆
     * @param role
     * @param username
     * @param password
     * @return
     */
    public Map<String,Object> userLogin(HttpServletRequest request, String role, String username, String password) {
        Map<String,Object> result = new HashMap<String,Object>();
        if("manager".equals(role)){
            //登陆用户为管理员
            Map<String,Object>data = new HashMap<>();
            data=userDao.adminLogin(username,password);

            if(data!=null && data.get("id")!=null){
                //登陆成功
                //获取session对象,然后把要绑定的值username，帮定到session对象上用户的一次会话共享一个session对象
                request.getSession().setAttribute("adminUserName",data.get("username"));
                request.getSession().setAttribute("adminName",data.get("name"));
                request.getSession().setAttribute("adminId",data.get("id"));
                result.put("code",true);
                result.put("url","/index.jsp");
                return result;
            }else{
                result.put("code",false);
                result.put("msg","管理员用户名或密码错误！");
                return result;
            }
        }
        else{
                //从user表中找
                Map<String,Object> stu1 = userDao.studentLogin1(username,password);
                if(stu1==null) {
                    result.put("code", false);
                    result.put("msg", "用户名或密码错误！");
                    return result;
                }else{
                    int stuId = (Integer)stu1.get("stuId");
                    //查找用户名
                    String name = userDao.getUsername(stuId);
                    request.getSession().setAttribute("userName",name);
                    request.getSession().setAttribute("userId",stuId);
                    result.put("code", true);
                    result.put("url","/stuMsg.jsp");
                    return result;
                }
        }
    }
    /**
     * 用户注销
     */
    public void UserLogout(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session=request.getSession();

        if(session.getAttribute("userName")!=null)
        {
            session.removeAttribute("userName");
            session.invalidate();
        }
        response.sendRedirect("/login.jsp");
    }
    /**
     * 管理员注销
     */
    public void AdminLogout(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session=request.getSession();
        if(session.getAttribute("adminName")!=null)
        {
            session.removeAttribute("adminName");
            session.invalidate();
        }
        response.sendRedirect("/login.jsp");
    }
}
