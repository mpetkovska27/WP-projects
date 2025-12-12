package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Chef chef;

    public enum Cuisine{
        ITALIAN, BRITISH, MEXICAN
    }

    private LocalDate preparationDate;

    public Dish(String dishId, String name, Cuisine cuisine, int preparationTime, LocalDate preparationDate) {
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
        this.preparationDate=preparationDate;
    }
}