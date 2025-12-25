package mk.ukim.finki.wp.lab.repository;

import java.util.List;
import java.util.Optional;

import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    Dish findByDishId(String dishId);

    @Query("SELECT d FROM Dish d JOIN d.chefs c WHERE c.id = :chefId")
    List<Dish> findAllByChef_Id(@Param("chefId") Long chefId);
}