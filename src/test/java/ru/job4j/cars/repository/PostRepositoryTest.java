package ru.job4j.cars.repository;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

class PostRepositoryTest {
    private final CrudRepository crudRepository = new CrudRepository(
            new MetadataSources(new StandardServiceRegistryBuilder()
                    .configure().build()).buildMetadata().buildSessionFactory());
    private final PostRepository postRepository = new PostRepository(crudRepository);

    @BeforeEach
    public void clearDataBase() {
        crudRepository.tx((Function<Session, Object>)
                session -> session.createQuery("delete from Post").executeUpdate());
    }

    @Test
    public void whenFindPostToLastDay() {
        Post oldPost = new Post(
                1,
                "text",
                LocalDateTime.of(2000, 1, 10, 14, 15),
                new User(),
                new HashSet<>()
        );
        Post newPost = new Post(
                2,
                "text2",
                LocalDateTime.of(2022, 10, 6, 10, 00),
                new User(),
                new HashSet<>()
        );
        postRepository.add(oldPost);
        postRepository.add(newPost);
        Assertions.assertThat(postRepository.findPostToLastDay()).isEqualTo(newPost);
    }

    @Test
    public void whenFindPostWithPhoto() {
        Post post = new Post();
        post.setText("post_text");
        postRepository.add(post);
        Post savedPost = (Post) postRepository.findPostWithPhoto();
        Assertions.assertThat(savedPost.getText()).isEqualTo(post.getText());
    }

    @Test
    public void whenFindPostWithModel() {
        Post firstPost = new Post();
        Post secondPost = new Post();
        String key = "key";
        firstPost.setText("key_of_post");
        secondPost.setText("post_of_key");
        postRepository.add(firstPost);
        postRepository.add(secondPost);
        Assertions.assertThat(postRepository.findPostWithModel(key)).isEqualTo(List.of(firstPost, secondPost));
    }
}