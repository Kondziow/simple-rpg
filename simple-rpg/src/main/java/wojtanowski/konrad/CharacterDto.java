package wojtanowski.konrad;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CharacterDto {
    private String name;
    private int level;
    private String profession;
}
