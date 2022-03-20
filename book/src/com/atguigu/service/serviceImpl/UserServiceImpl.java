package com.atguigu.service.serviceImpl;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.daoImpl.UserDaoImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;

/**
 * @Author Mia
 * Date  2022/1/31 16:50
 * Description
 */
public class UserServiceImpl implements UserService {
    UserDao dao = new UserDaoImpl();

    @Override
    public User login(User user) {
        return dao.queryByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void register(User user) {
        dao.saveUser(user);
    }

    @Override
    public boolean existsUsername(String username) {
        User user = dao.queryByUsername(username);
        if (user != null)
            return true;
        else
            return false;
    }
}
