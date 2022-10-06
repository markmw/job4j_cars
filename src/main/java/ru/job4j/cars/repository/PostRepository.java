package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Repository
@AllArgsConstructor
public class PostRepository {
    private final CrudRepository crudRepository;

    public Post save(Post post) {
        crudRepository.tx((Consumer<Session>) session -> session.persist(post));
        return post;
    }

    public List<Post> getLastDay() {
        return crudRepository.getList(
                "from Post where created = (select max(created) from Post)", Post.class);
    }

    public List<Post> like(String key) {
        return crudRepository.getList(
                "from Post where text like :key",
                Map.of("key", "%" + key.toLowerCase() + "%"), Post.class);
    }

    public Optional<Post> getById(int id) {
        return crudRepository.getUniqResult(
                "from Post where id = :id", Map.of("id", id), Post.class);
    }
}
