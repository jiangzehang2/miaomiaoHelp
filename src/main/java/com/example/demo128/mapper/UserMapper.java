package com.example.demo128.mapper;

import com.example.demo128.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;


@Repository

public interface UserMapper {

    @Insert("INSERT into tab(name,password) value (#{name},#{password})")
    int saveUser(@Param("name") String name, @Param("password") String password);

   @Select("select id,name,password from teb where name=#{name}")
   User selectuser(@Param("name") String name);
}
