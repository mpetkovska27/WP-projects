package mk.ukim.finki.wp.lab.repository.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InMemoryDishRepository implements DishRepository {

    @Override
    public List<Dish> findAll() {
        return DataHolder.dishes;
    }

    @Override
    public Dish findByDishId(String dishId) {
        return DataHolder.dishes.stream().filter(c->c.getDishId().equals(dishId)).findFirst().orElse(null);
    }
}
