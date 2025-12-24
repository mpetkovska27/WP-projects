package mk.ukim.finki.wp.lab.service;

import java.util.List;

import mk.ukim.finki.wp.lab.model.Dish;

public interface DishService {
    List<Dish> listDishes();
    Dish findByDishId(String dishId);
    Dish findById(Long id);
    Dish create(String dishId, String name, String cuisine, int preparationTime);
    Dish update(Long id, String dishId, String name, String cuisine, int preparationTime);
    void delete(Long id);
}