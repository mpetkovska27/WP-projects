package mk.ukim.finki.wp.lab.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "chefs")
@Data
@NoArgsConstructor
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String bio;

    @ManyToMany(mappedBy = "chefs")
    private List<Dish> dishes;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender{
        NOTDEFINED, MAN, FEMALE
    }

    public Chef(String firstName, String lastName, String bio, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.gender = gender;
        this.dishes = new ArrayList<>();
    }
}