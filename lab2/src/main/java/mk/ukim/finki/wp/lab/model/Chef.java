package mk.ukim.finki.wp.lab.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Chef {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private List<Dish> dishes;
    private Gender gender;


    public enum Gender{
        NOTDEFINED, MAN, FEMALE
    }

    public Chef(String firstName, String lastName, String bio, Gender gender) {
        this.id=(long) (Math.random() * 1000);
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.gender = gender;
    }
}