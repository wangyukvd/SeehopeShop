package com.seehope.service;

import com.seehope.entity.User;
import com.seehope.mapper.UserMapper;
import com.seehope.util.MyBatisUtil;
import org.apache.ibatis.ognl.security.UserMethod;
import org.apache.ibatis.session.SqlSession;

public class UserService {

    //6.创建com.seehope.service包下创建UserService类，调用Dao层相关方法。
        //业务层
    SqlSession sqlSsession= MyBatisUtil.getSession();
    UserMapper userMapper=sqlSsession.getMapper(UserMapper.class);

    /**
     * 注册
     *
     * @param user
     * @return SQLException
     */
    public boolean register(User user){

        int row = 0;
        row = userMapper.register(user);
        sqlSsession.commit();
        return row > 0 ? true : false;
    }
    /**
     * 校验用户名是否存在
     *
     * @param username
     * @return
     */


    public boolean checkUsername(String username){

        User user=userMapper.findUserByName(username);
        if(user==null){
            return false;
        }
        return true;
    }

    public User login(String username,String password){
        return userMapper.findUserByUserNameAndPassword(username,password);
    }

}
