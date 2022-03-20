package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.serviceImpl.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @Author Mia
 * Date  2022/2/9 10:49
 * Description
 */
public class UserServlet extends BaseServlet{
    private UserService userService = new UserServiceImpl();
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //获取session中的验证码
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //封装成user
        User user = new User(null,username,password,email,null);
        if(code.equalsIgnoreCase(token) && token!=null){
            if(userService.existsUsername(username)){
                //如果用户名已存在则跳转至登录页面
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            }
            else{
                //调用service注册用户
                userService.register(user);
                //跳转至注册成功页面
                resp.sendRedirect(req.getContextPath()+"/pages/user/regist_success.jsp");
            }
        }
        else{
            //如果验证码错误，跳转至注册页面
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
        if(token==null){
            System.out.println("请不要重复提交表单");
        }
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //验证用户名和密码
        User user = userService.login(new User(null, username, password, null, null));
        //将用户存存储在req域中
        req.setAttribute("username", username);
        req.getSession().setAttribute("user",user);
        if (user == null) {
            req.setAttribute("msg","用户名或密码错误");
            //登录失败，跳转至登录界面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
        else
            //登录成功，跳转至登录成功界面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //销毁session中用户登录信息
        req.getSession().invalidate();
        //重定向至首页
        resp.sendRedirect(req.getContextPath());
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取请求参数
        String username = req.getParameter("username");
        //调用service判断用户名是否存在
        boolean existsUsername = userService.existsUsername(username);
        //将结果封装到Map对象中
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        //将Map对象转换为Json字符串
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}
