package wojtanowski.konrad.simple_rpg.hero.service.api;

import org.springframework.data.jpa.repository.Query;
import wojtanowski.konrad.simple_rpg.hero.entity.Hero;

import java.util.List;
import java.util.UUID;

public interface HeroService {
    List<Hero> findAll();
    Hero save(Hero hero);
    void deleteById(UUID id);
    List<Hero> findByName(String name);
    List<Hero> findByNameAndServesDarkness(String name, boolean servesDarkness);
}
