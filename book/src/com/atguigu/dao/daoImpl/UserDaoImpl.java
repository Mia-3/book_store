package com.atguigu.dao.daoImpl;

import com.atguigu.dao.UserDao;
import com.atguigu.pojo.User;

/**
 * @Author Mia
 * Date  2022/1/31 16:36
 * Description
 */
public class UserDaoImpl extends BaseDao implements UserDao{
    private BaseDao dao = new BaseDao();
    @Override
    public User queryByUsername(String username) {
        String sql = "SELECT *FROM t_user WHERE username=?";
        return dao.queryForOne(User.class,sql,username);
    }

    @Override
    public User queryByUsernameAndPassword(String usernam, String password) {
        String sql = "SELECT * FROM t_user WHERE username=? AND password=?";
        return dao.queryForOne(User.class,sql,usernam,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "INSERT INTO t_user(username,password,email) VALUES(?,?,?)";
        return dao.update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
