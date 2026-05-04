package wojtanowski.konrad.simple_rpg.hero.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import wojtanowski.konrad.simple_rpg.species.entity.Species;

import java.io.Serializable;
import java.util.Comparator;
import java.util.UUID;


@Entity(name = "heroes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hero implements Comparable<Hero>, Serializable {
    @Id
    private UUID id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;

    private boolean servesDarkness;

    @NotNull(message = "Hero must have a species")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "species_id")
    @ToString.Exclude
    private Species species;

    @Override
    public int compareTo(Hero hero) {
        return Comparator.comparing(Hero::getName)
                .thenComparing(Hero::isServesDarkness)
                .thenComparing(Hero::getSpecies)
                .compare(this, hero);
    }
}
