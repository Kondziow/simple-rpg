package wojtanowski.konrad.simple_rpg.species.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import wojtanowski.konrad.simple_rpg.hero.entity.Hero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Entity(name = "species")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Species implements Comparable<Species>, Serializable {
    @Id
    private UUID id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;

    @Column(name = "base_health")
    @PositiveOrZero(message = "Base health must be a positive number or zero")
    private int baseHealth;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "species", fetch = FetchType.LAZY)
    private List<Hero> heroes = new ArrayList<>();

    @Override
    public int compareTo(Species species) {
        return Comparator.comparing(Species::getName)
                .thenComparing(Species::getBaseHealth)
                .compare(this, species);
    }
}
