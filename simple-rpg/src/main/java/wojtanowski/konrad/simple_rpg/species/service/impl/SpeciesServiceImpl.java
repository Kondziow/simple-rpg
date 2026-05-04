package wojtanowski.konrad.simple_rpg.species.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wojtanowski.konrad.simple_rpg.species.entity.Species;
import wojtanowski.konrad.simple_rpg.species.repository.SpeciesRepository;
import wojtanowski.konrad.simple_rpg.species.service.api.SpeciesService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeciesServiceImpl implements SpeciesService {
    private final SpeciesRepository speciesRepository;

    @Override
    public List<Species> findAll() {
        return speciesRepository.findAll();
    }
}
