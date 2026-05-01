package wojtanowski.konrad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hero implements Comparable<Hero>, Serializable {
    private String name;
    private boolean servesDarkness;
    private Species species;

    @Override
    public int compareTo(Hero hero) {
        return Comparator.comparing(Hero::getName)
                .thenComparing(Hero::isServesDarkness)
                .thenComparing(Hero::getSpecies)
                .compare(this, hero);
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", servessDarkness=" + servesDarkness +
                ", species=" + species.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Hero that)) return false;

        return servesDarkness == that.servesDarkness &&
                Objects.equals(name, that.name) &&
                Objects.equals(species, that.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, servesDarkness, species);
    }
}
