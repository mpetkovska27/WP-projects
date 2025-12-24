package mk.ukim.finki.wp.lab.repository;

import java.util.List;
import java.util.Optional;

import mk.ukim.finki.wp.lab.model.Dish;

public interface DishRepository {
    List<Dish> findAll();
    Dish findByDishId(String dishId);
    Optional<Dish> findById(Long id);
    Dish save(Dish dish);
    void deleteById(Long id);
}