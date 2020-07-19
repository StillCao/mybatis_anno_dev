package com.sean.test;

import com.sean.dao.IUserDao;
import com.sean.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Fun:
 * Created by CW on 2020/7/18 3:15 下午
 */
public class AnnotationCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("saveUser2");
        user.setAddress("湖北武汉");

        userDao.saveUser(user);

    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(49);
        user.setUsername("saveUser1");
        user.setAddress("湖北黄冈");
        user.setSex("男");
        user.setBirthday(new Date());
        userDao.updateUser(user);

    }

    /**
     * 根据id删除用户
     */
    @Test
    public void testDelete(){
        userDao.deleteUser(50);
    }

    /**
     * 根据id,查找用户信息
     */
    @Test
    public void testFindOne(){
        User user = userDao.findById(49);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){
        List<User> users = userDao.findUserByName("%王%");
        for(User user : users){
            System.out.println(user);
        }
    }

    /*一对多注解*/
    @Test
    public void testUserAndCount(){
        User userAndCounts = userDao.findUserAndCount(46);
        System.out.println(userAndCounts);
    }
}
