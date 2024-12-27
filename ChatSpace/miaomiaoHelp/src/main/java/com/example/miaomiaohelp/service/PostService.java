package com.example.miaomiaohelp.service;/**
 * @BelongsProject: miaomiaoHelp
 * @BelongsPackage: com.example.miaomiaohelp.service
 * @ClassName PostService
 * @Author: bill
 * @CreateTime: 2024-12-13  20:28
 * @Description: TODO
 * @Version: 1.0
 */

/**
 * 服务层接口 提供保存和获取帖子信息的功能
 * @author dell
 */
import com.example.miaomiaohelp.entity.Post;
import com.example.miaomiaohelp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * PostService 服务层
 * 提供保存和获取帖子信息的功能。
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    /**
     * 保存帖子
     * @param post Post 实体
     * @return 保存后的 Post 实体
     */
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    /**
     * 获取所有帖子
     * @return 帖子列表
     */
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}

