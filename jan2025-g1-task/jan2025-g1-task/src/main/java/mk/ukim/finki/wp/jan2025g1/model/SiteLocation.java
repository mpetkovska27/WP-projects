package mk.ukim.finki.wp.jan2025g1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table
public class SiteLocation {
    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String country;

    public SiteLocation(String city, String country) {
        this.city = city;
        this.country = country;
    }
}
