package mk.ukim.finki.wp.lab.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.DishService;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    @Override
    public List<Dish> listDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return dishRepository.findByDishId(dishId);
    }

    @Override
    public Dish findById(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found with id: " + id));
    }

    @Override
    public Dish create(String dishId, String name, Dish.Cuisine cuisine, int preparationTime, LocalDate preparationDate) {
        return dishRepository.save(new Dish(dishId, name, cuisine, preparationTime, preparationDate));
    }

    @Override
    public Dish update(Long id, String dishId, String name, Dish.Cuisine cuisine, int preparationTime, LocalDate preparationDate) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found with id: " + id));

        dish.setDishId(dishId);
        dish.setName(name);
        dish.setCuisine(cuisine);
        dish.setPreparationTime(preparationTime);
        dish.setPreparationDate(preparationDate);
        return dishRepository.save(dish);
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }

    @Override
    public List<Dish> findAllByChef_Id(Long chefId) {
        return dishRepository.findAllByChef_Id(chefId);
    }


}
