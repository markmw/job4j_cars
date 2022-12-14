package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final CrudRepository crudRepository;

    public User create(User user) {
        crudRepository.tx((Consumer<Session>) session -> session.persist(user));
        return user;
    }

    public void update(User user) {
        crudRepository.tx((Consumer<Session>) session -> session.merge(user));
    }

    public void delete(User user) {
        crudRepository.tx((Consumer<Session>) session -> session.delete(user));
    }

    public List<User> findAllOrderById() {
        return crudRepository.getList("from User order by id", User.class);
    }

    public Optional<User> findById(int userId) {
        return crudRepository.getUniqResult(
                "from User where id = :userId",
                Map.of("userId", userId), User.class);
    }

    public List<User> findByLikeLogin(String key) {
        return crudRepository.getList(
                "from User where login like :key",
                Map.of("key", "%" + key.toLowerCase() + "%"), User.class);
    }

    public Optional<User> findByLogin(String key) {
        return crudRepository.getUniqResult(
                "from User where login like :key",
                Map.of("key", key), User.class);
    }
}
