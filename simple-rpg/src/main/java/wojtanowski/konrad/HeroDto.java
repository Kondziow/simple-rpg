package wojtanowski.konrad;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HeroDto {
    private String name;
    private boolean servesDarkness;
    private String species;
}
