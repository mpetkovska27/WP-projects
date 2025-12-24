package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init() {


        Dish dish1 = new Dish("1", "Pasta Carbonara", "Italian", 20);
        Dish dish2 = new Dish("2", "Beef Wellington", "British", 45);
        Dish dish3 = new Dish("3", "Chicken Tikka Masala", "Indian", 35);
        Dish dish4 = new Dish("4", "Sushi Roll", "Japanese", 25);
        Dish dish5 = new Dish("5", "Chocolate Cake", "Dessert", 50);

        dishes.add(dish1);
        dishes.add(dish2);
        dishes.add(dish3);
        dishes.add(dish4);
        dishes.add(dish5);

        Chef chef1 = new Chef(1L, "Ana", "Anovska", "Bio na Ana", new ArrayList<>(), Chef.Gender.FEMALE);
        Chef chef2 = new Chef(2L, "Marija", "Markovska", "Bio na Marija", new ArrayList<>(), Chef.Gender.FEMALE);
        Chef chef3 = new Chef(3L, "Mihaela", "Mihajlovska", "Bio na Mihaela", new ArrayList<>(), Chef.Gender.FEMALE);
        Chef chef4 = new Chef(4L, "Marko", "Markov", "Bio na Marko", new ArrayList<>(), Chef.Gender.MAN);
        Chef chef5 = new Chef(5L, "Matej", "Petkov", "Bio na Matej", new ArrayList<>(), Chef.Gender.MAN);

        chefs.add(chef1);
        chefs.add(chef2);
        chefs.add(chef3);
        chefs.add(chef4);
        chefs.add(chef5);
    }
}
