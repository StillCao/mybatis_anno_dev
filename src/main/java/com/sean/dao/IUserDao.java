package com.sean.dao;

import com.sean.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Fun:
 * Created by CW on 2020/7/18 2:32 下午
 * 在MyBatis中针对CRUD一共有4种注解
 * 如： @Select,@Update,@Delete,@Insert
 */
public interface IUserDao {
    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
   /* @Results(id = "userMap",value = {
            @Result(id = true,column = "id" ,property = "u_id"),
            @Result(column = "username" ,property = "u_name"),
            @Result(column = "address" ,property = "u_address"),
            @Result(column = "sex" ,property = "u_sex"),
            @Result(column = "birthday" ,property = "u_birthday"),
    }) */
   //如果数据库表字段名和JavaBean成员名不对应，可以像这样写对照集；也可以在sql查询时起别名。
    //如果其它方法想引用对照集：@ResultMap({"userMap"})
    List<User> findAll();


    /**
     * 保存用户
     */
    @Insert("insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    /**
     * 更新用户信息
     * @param user
     */
    @Update("update user set username = #{username},sex = #{sex},birthday = #{birthday},address = #{address} where id = #{id}")
    void updateUser(User user);

    /**
     * 根据id,删除用户
     * @param id
     */
    @Delete("delete  from user where id = #{id}")
    void deleteUser(Integer id);

    /**
     * 根据id查找用户
     */
    @Select("select * from user where id = #{id}")
    User findById(Integer id);


    /**
     * 根据用户名模糊查询
     */
//    @Select("select * from user where username like '%${value}%' ")
    @Select("select * from user where username like #{username}")    //传入的字符需要带%号
    List<User> findUserByName(String username);

    /**
     *
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    @Results(id = "userMap",value = {
            @Result(column = "id",property = "accounts",many = @Many(select = "com.sean.dao.IAccountDao.findAccountByUid",fetchType = FetchType.LAZY))
    })
    User findUserAndCount(Integer id);

}
