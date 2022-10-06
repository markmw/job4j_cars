package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PostRepository {
    private final CrudRepository crudRepository;

    public List<Post> findPostToLastDay() {
        return crudRepository.query(
                "SELECT post FROM Post p JOIN FETCH post.priceHistory h "
                        + "JOIN FETCH p.car c JOIN FETCH p.participates participates "
                        + "WHERE p.created BETWEEN :aYesterday and :aToday "
                        + "ORDER BY p.created DESC", Post.class,
                Map.of("aYesterday", Timestamp.valueOf(LocalDateTime.now().minusDays(1)),
                        "aToday", Timestamp.valueOf(LocalDateTime.now())));
    }

    public List<Post> findPostWithPhoto() {
        return crudRepository.query(
                "SELECT post FROM Post p JOIN FETCH p.priceHistory h "
                        + "JOIN FETCH p.car c JOIN FETCH p.participates participates "
                        + "WHERE a.photo.size > 0 ORDER BY p.created DESC", Post.class);
    }

    public List<Post> findPostWithModel(String model) {
        return crudRepository.query(
                "SELECT post FROM Post p JOIN FETCH post.priceHistory h "
                        + "JOIN FETCH p.car c JOIN FETCH p.participates participates "
                        + "WHERE c.model = :fModel ORDER BY p.created DESC",
                Post.class,
                Map.of("fModel", model)
        );
    }

    public Post add(Post post) {
        crudRepository.run(session -> session.persist(post));
        return post;
    }
}
