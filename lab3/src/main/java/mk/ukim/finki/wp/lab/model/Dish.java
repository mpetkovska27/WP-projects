package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "dishes")
@Data
@NoArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dishId;
    private String name;

    @Enumerated(EnumType.STRING)
    private Cuisine cuisine;
    private int preparationTime;

    @ManyToMany
    @JoinTable(
            name = "dish_chef",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "chef_id")
    )
    private List<Chef> chefs;

    private LocalDate preparationDate;

    public enum Cuisine {
        ITALIAN, BRITISH, MEXICAN
    }

    public Dish(String dishId, String name, Cuisine cuisine, int preparationTime, LocalDate preparationDate) {
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
        this.preparationDate = preparationDate;
    }
}