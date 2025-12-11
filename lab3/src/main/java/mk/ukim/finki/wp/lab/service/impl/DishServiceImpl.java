package mk.ukim.finki.wp.lab.service.impl;

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
    public Dish create(String dishId, String name, String cuisine, int preparationTime) {
        return dishRepository.save(new Dish(dishId, name, cuisine, preparationTime));
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime) {
        Dish dish=new Dish(dishId,name,cuisine,preparationTime);
        dish.setId(id);     //se setira id-to
        dishRepository.save(dish);
        return dish;
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }
}
