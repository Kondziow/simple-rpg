package wojtanowski.konrad.simple_rpg.hero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wojtanowski.konrad.simple_rpg.hero.entity.Hero;
import wojtanowski.konrad.simple_rpg.species.entity.Species;

import java.util.List;
import java.util.UUID;

@Repository
public interface HeroRepository extends JpaRepository<Hero, UUID> {
    List<Hero> findBySpecies(Species species);
    List<Hero> findByNameIgnoreCase(String name);
    @Query("SELECT h FROM heroes h WHERE LOWER(h.name) = LOWER(:name) AND h.servesDarkness = :servesDarkness")
    List<Hero> findByNameAndServesDarkness(String name, boolean servesDarkness);
}
