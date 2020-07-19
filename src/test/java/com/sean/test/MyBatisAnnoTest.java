package com.sean.test;

import com.sean.dao.IUserDao;
import com.sean.domain.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Fun:
 * Created by CW on 2020/7/18 2:50 下午
 */

//为该类加一个 CacheNamespace()注解，开启二级缓存，也就是SqlSessionFactory创建的SqlSession对象共享的缓存
@CacheNamespace(blocking = true)
public class MyBatisAnnoTest {

    public static void main(String[] args) throws IOException {
        //1.获取字节输入流
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.根据字节输入流构建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.根据SqlSessionFactory生产一个SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession获取Dao代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5.执行Dao的方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();
    }
}
