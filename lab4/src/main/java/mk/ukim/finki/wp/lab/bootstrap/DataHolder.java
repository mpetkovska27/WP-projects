package mk.ukim.finki.wp.lab.bootstrap;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataHolder {
    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;

    @PostConstruct
    public void init() {
        if (dishRepository.count() == 0) {
            Dish dish1 = new Dish("1", "Pasta Carbonara", Dish.Cuisine.ITALIAN, 20, LocalDate.now());
            Dish dish2 = new Dish("2", "Beef Wellington", Dish.Cuisine.BRITISH, 45, LocalDate.now());
            Dish dish3 = new Dish("3", "Chicken Tikka Masala", Dish.Cuisine.MEXICAN, 35, LocalDate.now());
            Dish dish4 = new Dish("4", "Sushi Roll", Dish.Cuisine.ITALIAN, 25, LocalDate.now());
            Dish dish5 = new Dish("5", "Chocolate Cake", Dish.Cuisine.BRITISH, 50, LocalDate.now());

            dishRepository.save(dish1);
            dishRepository.save(dish2);
            dishRepository.save(dish3);
            dishRepository.save(dish4);
            dishRepository.save(dish5);
        }

        if (chefRepository.count() == 0) {
            Chef chef1 = new Chef("Ana", "Anovska", "Bio na Ana", Chef.Gender.FEMALE);
            Chef chef2 = new Chef("Marija", "Markovska", "Bio na Marija", Chef.Gender.FEMALE);
            Chef chef3 = new Chef("Mihaela", "Mihajlovska", "Bio na Mihaela", Chef.Gender.FEMALE);
            Chef chef4 = new Chef("Marko", "Markov", "Bio na Marko", Chef.Gender.MAN);
            Chef chef5 = new Chef("Matej", "Petkov", "Bio na Matej", Chef.Gender.MAN);

            chefRepository.save(chef1);
            chefRepository.save(chef2);
            chefRepository.save(chef3);
            chefRepository.save(chef4);
            chefRepository.save(chef5);
        }
    }
}
