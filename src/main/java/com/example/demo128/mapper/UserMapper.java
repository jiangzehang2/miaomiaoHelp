package com.example.demo128.mapper;

import com.example.demo128.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository

public interface UserMapper extends CrudRepository<User,Integer>  {

    @Insert("INSERT into tab(phoneNumber,password) value (#{phoneNumber},#{password})")
    int saveUser(@Param("phoneNumber") String phoneNumber, @Param("password") String password);

    @Select("select id,phoneNumber,password from tab where phoneNumber=#{phoneNumber}")
    User selectuser(@Param("phoneNumber") String phoneNumber);



    // 更新用户密码（明文）
    @Update("UPDATE users SET password = #{password} WHERE username = #{username}")
    static User updateUserPassword(@Param("phoneNumber") String phoneNumber, @Param("password") String password) {
        return null;
    }



}
