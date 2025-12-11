package mk.ukim.finki.wp.lab.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;

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

    @Override
    public Optional<Dish> findById(Long id) {
        return DataHolder.dishes.stream()
                .filter(dish -> dish.getId().equals(id))
                .findFirst();
    }

    @Override
    public Dish save(Dish dish) {
        //prvo proverka dali postoi toj dish
        Optional<Dish> optionalDish=findById(dish.getId());
        if(optionalDish.isPresent()){
            Dish existingDish=optionalDish.get();
            existingDish.setName(dish.getName());
            existingDish.setCuisine(dish.getCuisine());
            existingDish.setPreparationTime(dish.getPreparationTime());
            existingDish.setDishId(dish.getDishId());
        } else {
            DataHolder.dishes.add(dish);
        }
        return dish;
    }

    @Override
    public void deleteById(Long id) {
        DataHolder.dishes.removeIf(dish -> dish.getId().equals(id));
    }
}
