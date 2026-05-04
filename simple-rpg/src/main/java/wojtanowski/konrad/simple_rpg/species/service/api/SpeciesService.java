package wojtanowski.konrad.simple_rpg.species.service.api;

import wojtanowski.konrad.simple_rpg.species.entity.Species;

import java.util.List;

public interface SpeciesService {
    List<Species> findAll();
}
