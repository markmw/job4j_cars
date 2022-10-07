package ru.job4j.cars.repository;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

class PostRepositoryTest {
    private final CrudRepository crudRepository = new CrudRepository(
            new MetadataSources(new StandardServiceRegistryBuilder()
                    .configure().build())
                    .buildMetadata().buildSessionFactory());
    private final PostRepository postRepository = new PostRepository(crudRepository);

    @BeforeEach
    public void clearDataBase() {
        crudRepository.tx((Function<Session, Object>) session -> session.createQuery(
                "delete from Post").executeUpdate());
    }

    @Test
    public void whenSavePost() {
        Post post = new Post();
        post.setText("post_text");
        postRepository.save(post);
        Post savedPost = postRepository.getById(post.getId()).orElse(new Post());
        Assertions.assertThat(savedPost.getText()).isEqualTo(post.getText());
    }

    @Test
    public void whenGetPostWithTextLikeKey() {
        Post firstPost = new Post();
        Post secondPost = new Post();
        String key = "key";
        firstPost.setText("key_of_post");
        secondPost.setText("post_of_key");
        postRepository.save(firstPost);
        postRepository.save(secondPost);
        Assertions.assertThat(postRepository.like(key)).isEqualTo(List.of(firstPost, secondPost));
    }

    @Test
    public void whenGetLastDayPost() {
        Post oldPost = new Post();
        Post newPost = new Post();
        LocalDateTime oldDateTime = LocalDateTime.of(2000, 1, 10, 14, 15);
        LocalDateTime newDateTime = LocalDateTime.of(2010, 1, 10, 14, 15);
        oldPost.setCreated(oldDateTime);
        newPost.setCreated(newDateTime);
        postRepository.save(oldPost);
        postRepository.save(newPost);
        Post post = postRepository.getLastDay().get(0);
        Assertions.assertThat(postRepository.getLastDay().get(0)).isEqualTo(newPost);
    }
}