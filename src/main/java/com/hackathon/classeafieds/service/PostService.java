package com.hackathon.classeafieds.service;

import com.hackathon.classeafieds.model.Post;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    Iterable<Post> findAll();
    Iterable<Post> findAllByUser();
    Post findById(Long id);
    void save(Post post, MultipartFile file);
    void update(Post post, MultipartFile file);
    void delete(Post post);
    Iterable<Post> search(String q);
}
