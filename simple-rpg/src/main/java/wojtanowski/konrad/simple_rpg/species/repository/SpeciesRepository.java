package wojtanowski.konrad.simple_rpg.species.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wojtanowski.konrad.simple_rpg.species.entity.Species;

import java.util.UUID;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, UUID> {
}
