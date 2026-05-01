package wojtanowski.konrad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Species implements Comparable<Species>, Serializable {
    private String name;
    private int baseHealth;
    @Builder.Default
    private List<Hero> heroes = new ArrayList<>();

    @Override
    public int compareTo(Species species) {
        return Comparator.comparing(Species::getName)
                .thenComparing(Species::getBaseHealth)
                .compare(this, species);
    }

    @Override
    public String toString() {
        return "Species{" +
                "name='" + name + '\'' +
                ", baseHealth=" + baseHealth +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Species that)) return false;

        return baseHealth == that.baseHealth &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, baseHealth);
    }
}
