package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.PriceHistory;

import java.util.function.Consumer;

@Repository
@AllArgsConstructor
public class PriceHistoryRepository {
    private final CrudRepository crudRepository;

    public PriceHistory save(PriceHistory priceHistory) {
        crudRepository.tx((Consumer<Session>) session -> session.save(priceHistory));
        return priceHistory;
    }
}
