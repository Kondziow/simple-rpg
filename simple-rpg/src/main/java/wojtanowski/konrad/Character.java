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
public class Character implements Comparable<Character>, Serializable {
    private String name;
    private int level;
    private Profession profession;

    @Override
    public int compareTo(Character character) {
        return Comparator.comparing(Character::getName)
                .thenComparing(Character::getLevel)
                .thenComparing(Character::getProfession)
                .compare(this, character);
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", profession=" + profession.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Character that)) return false;

        return level == that.level &&
                Objects.equals(name, that.name) &&
                Objects.equals(profession, that.profession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, profession);
    }
}
