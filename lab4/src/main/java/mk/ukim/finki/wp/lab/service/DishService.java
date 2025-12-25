package mk.ukim.finki.wp.lab.service;

import java.time.LocalDate;
import java.util.List;

import mk.ukim.finki.wp.lab.model.Dish;

public interface DishService {
    List<Dish> listDishes();
    Dish findByDishId(String dishId);
    Dish findById(Long id);
    Dish create(String dishId, String name, Dish.Cuisine cuisine, int preparationTime, LocalDate preparationDate);
    Dish update(Long id, String dishId, String name, Dish.Cuisine cuisine, int preparationTime, LocalDate preparationDate);
    void delete(Long id);
    List<Dish> findAllByChef_Id(Long chefId);
}