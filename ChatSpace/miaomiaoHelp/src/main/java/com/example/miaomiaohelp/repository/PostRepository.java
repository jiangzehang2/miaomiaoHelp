package com.example.miaomiaohelp.repository;/**
 * @BelongsProject: miaomiaoHelp
 * @BelongsPackage: com.example.miaomiaohelp.repository
 * @ClassName PostRepository
 * @Author: bill
 * @CreateTime: 2024-12-13  20:26
 * @Description: TODO
 * @Version: 1.0
 */

/**
 * 数据库访问层次接口 用于操作posts表中的数据
 * @author dell
 */

import com.example.miaomiaohelp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PostRepository 数据访问层接口
 * 用于操作 posts 表中的数据。
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
