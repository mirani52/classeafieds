package com.hackathon.classeafieds.service;

import com.hackathon.classeafieds.dao.PostDao;
import com.hackathon.classeafieds.model.Post;
import com.hackathon.classeafieds.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;
    @Autowired
    private EvaluationContextExtension contextExtension;

    @Override
    public Iterable<Post> findAll() {
    	List<Post> allPosts = (List<Post>) postDao.findAll();
    	Collections.reverse(allPosts);
        return allPosts;
    }

    @Override
    public Iterable<Post> findAllByUser() {
    	List<Post> postsByUser =(List<Post>) postDao.findAllByUser();
    	Collections.reverse(postsByUser);
    	return postsByUser;
    }

    @Override
    public Post findById(Long id) {
        return postDao.findOne(id);
    }

    @Override
    public void save(Post post, MultipartFile file) {
        try {
            post.setBytes(file.getBytes());
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            post.setEmail(user.getUsername());

            // Set status to true if it is for rent
            if(!post.isBuy())
                post.setRentStatus(true);

            postDao.save(post);
        } catch (IOException e) {
            System.err.println("Unable to get byte array from the uploaded file!");
        }
    }

    @Override
    public void update(Post post, MultipartFile file) {
        try {
            if(!file.isEmpty())
                post.setBytes(file.getBytes());
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            post.setEmail(user.getUsername());


            postDao.save(post);
        } catch (IOException e) {
            System.err.println("Unable to get byte array from the uploaded file!");
        }
    }

    @Override
    public void delete(Post post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(post.getUser().getId().equals(user.getId()))
            postDao.delete(post);
    }

    @Override
    public Iterable<Post> search(String q) {
        HashSet<Post> posts = new HashSet<>();
        
        List<Post> byTitlePosts = postDao.searchPostByTitle(q);
        Collections.reverse(byTitlePosts);
        
        List<Post> byDescriptionPosts = postDao.searchPostByDescription(q);
        Collections.reverse(byDescriptionPosts);
        
        posts.addAll(byTitlePosts);
        posts.addAll(byDescriptionPosts);
        
        return posts;
    }
}
