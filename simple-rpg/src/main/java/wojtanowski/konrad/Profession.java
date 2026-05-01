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
public class Profession implements Comparable<Profession>, Serializable {
    private String name;
    private int baseArmor;
    @Builder.Default
    private List<Character> characters = new ArrayList<>();

    @Override
    public int compareTo(Profession profession) {
        return Comparator.comparing(Profession::getName)
                .thenComparing(Profession::getBaseArmor)
                .compare(this, profession);
    }

    @Override
    public String toString() {
        return "Profession{" +
                "name='" + name + '\'' +
                ", baseArmor=" + baseArmor +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Profession that)) return false;

        return baseArmor == that.baseArmor &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, baseArmor);
    }
}
