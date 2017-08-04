package com.hackathon.classeafieds.dao;

import com.hackathon.classeafieds.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao extends CrudRepository<Post, Long> {
    @Query("Select p from Post p where p.user.id=:#{principal.id}")
    List<Post> findAllByUser();

    @Query("SELECT p FROM Post p WHERE p.title LIKE CONCAT('%',:word,'%')")
    List<Post> searchPostByTitle(@Param("word") String word);

    @Query("SELECT p FROM Post p WHERE p.description LIKE CONCAT('%',:word,'%')")
    List<Post> searchPostByDescription(@Param("word") String word);
}
