package wojtanowski.konrad.simple_rpg.hero.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wojtanowski.konrad.simple_rpg.hero.entity.Hero;
import wojtanowski.konrad.simple_rpg.hero.repository.HeroRepository;
import wojtanowski.konrad.simple_rpg.hero.service.api.HeroService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroServiceImpl implements HeroService {
    private final HeroRepository heroRepository;

    @Override
    public List<Hero> findAll() {
        return heroRepository.findAll();
    }

    @Override
    public Hero save(Hero hero) {
        return heroRepository.save(hero);
    }

    @Override
    public void deleteById(UUID id) {
        heroRepository.deleteById(id);
    }

    @Override
    public List<Hero> findByName(String name) {
        return heroRepository.findByNameIgnoreCase(name);
    }

    @Override
    public List<Hero> findByNameAndServesDarkness(String name, boolean servesDarkness) {
        return heroRepository.findByNameAndServesDarkness(name, servesDarkness);
    }
}
