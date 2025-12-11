package mk.ukim.finki.wp.lab.repository;

import java.util.List;
import java.util.Optional;

import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    Dish findByDishId(String dishId);
    List<Dish> findAllByChef_Id(Long chefId);
}