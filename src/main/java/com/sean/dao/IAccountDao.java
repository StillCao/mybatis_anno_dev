package com.sean.dao;

import com.sean.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Fun:
 * Created by CW on 2020/7/18 5:26 下午
 */
public interface IAccountDao {

    /**
     * MyBatis提供的一对一的查询@One(select="",fetchType="")
     * 查询所有账户信息，并且获取每个账户所属的用户信息
     * @return
     */
    @Select("select * from account")
    @Results(id = "accountMap", value= {
         /*   @Result(id = true ,column = "id",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),*/
         //上面三行结果名称是对应的，可以不用写。重点是下面MyBatis使用@One注解，根据前面的查询Uid,去执行findById,封装到user中，
            //避免了使用左外连接查询。本质应该还是二次查询的过程。
            @Result(column = "uid",property = "user",one=@One(select = "com.sean.dao.IUserDao.findById",fetchType = FetchType.EAGER))
    })

    /*查找所有的账户*/
    List<Account> findAll();

    /*根据id查找用户的所有账户*/
    @Select("select * from Account where uid = #{uid}")
    List<Account> findAccountByUid(Integer uid);
}
