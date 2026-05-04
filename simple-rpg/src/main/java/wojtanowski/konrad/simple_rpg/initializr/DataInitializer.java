package wojtanowski.konrad.simple_rpg.initializr;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import wojtanowski.konrad.simple_rpg.hero.entity.Hero;
import wojtanowski.konrad.simple_rpg.hero.repository.HeroRepository;
import wojtanowski.konrad.simple_rpg.species.entity.Species;
import wojtanowski.konrad.simple_rpg.species.repository.SpeciesRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@AllArgsConstructor
public class DataInitializer implements InitializingBean {
    private final HeroRepository heroRepository;
    private final SpeciesRepository speciesRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (heroRepository.findAll().isEmpty()) {
            Species hobbit = Species.builder()
                    .id(UUID.fromString("195c2005-afb3-4158-b063-93dd2493bd78"))
                    .name("Hobbit")
                    .baseHealth(50)
                    .build();

            Species men = Species.builder()
                    .id(UUID.fromString("f2dcfbf1-8353-449e-bf68-48a98d4d7e28"))
                    .name("Men")
                    .baseHealth(100)
                    .build();

            Species elf = Species.builder()
                    .id(UUID.fromString("ed2a7618-f7d0-4a87-94b3-01963ad7f08e"))
                    .name("Elf")
                    .baseHealth(120)
                    .build();

            Species dwarf = Species.builder()
                    .id(UUID.fromString("f74e17c5-2aba-4d87-b610-fcd1a47982b5"))
                    .name("Dwarf")
                    .baseHealth(150)
                    .build();

            speciesRepository.save(men);
            speciesRepository.save(hobbit);
            speciesRepository.save(elf);
            speciesRepository.save(dwarf);

            Hero frodo = Hero.builder()
                    .id(UUID.fromString("1d4cdf77-c1e6-447b-a951-3cc57f6ae69b"))
                    .name("Frodo")
                    .servesDarkness(false)
                    .species(hobbit)
                    .build();

            Hero merry = Hero.builder()
                    .id(UUID.fromString("b2bd6c0d-7fd4-4f66-a93e-db545f4bbbf8"))
                    .name("Merry")
                    .servesDarkness(false)
                    .species(hobbit)
                    .build();

            Hero pippin = Hero.builder()
                    .id(UUID.fromString("15ee64b1-06f7-47f7-9a34-4c17045e98d2"))
                    .name("Pippin")
                    .servesDarkness(false)
                    .species(hobbit)
                    .build();

            Hero samwise = Hero.builder()
                    .id(UUID.fromString("3ae1043b-bcd9-493b-b775-07d3aade00d6"))
                    .name("Samwise")
                    .servesDarkness(false)
                    .species(hobbit)
                    .build();

            Hero aragorn = Hero.builder()
                    .id(UUID.fromString("c36169d2-7f20-4ce1-81b2-1dcc735817c6"))
                    .name("Aragorn")
                    .servesDarkness(false)
                    .species(men)
                    .build();

            Hero boromir = Hero.builder()
                    .id(UUID.fromString("b337ca22-15d8-4f4c-95b3-dc9503aeb3ec"))
                    .name("Boromir")
                    .servesDarkness(false)
                    .species(men)
                    .build();

            Hero gandalf = Hero.builder()
                    .id(UUID.fromString("4a51c767-9613-4a71-b373-b576f0cbd160"))
                    .name("Gandalf")
                    .servesDarkness(false)
                    .species(men)
                    .build();

            Hero legolas = Hero.builder()
                    .id(UUID.fromString("0e5e8969-cf5f-47ec-b817-95540666479a"))
                    .name("Legolas")
                    .servesDarkness(false)
                    .species(elf)
                    .build();

            Hero gimli = Hero.builder()
                    .id(UUID.fromString("6d97b024-d96d-4a90-ab61-4652b1c8ac18"))
                    .name("Gimli")
                    .servesDarkness(false)
                    .species(dwarf)
                    .build();

            heroRepository.save(frodo);
            heroRepository.save(merry);
            heroRepository.save(pippin);
            heroRepository.save(samwise);
            heroRepository.save(aragorn);
            heroRepository.save(boromir);
            heroRepository.save(gandalf);
            heroRepository.save(legolas);
            heroRepository.save(gimli);

            hobbit.getHeroes().add(frodo);
            hobbit.getHeroes().add(merry);
            hobbit.getHeroes().add(pippin);
            hobbit.getHeroes().add(samwise);
            men.getHeroes().add(aragorn);
            men.getHeroes().add(boromir);
            men.getHeroes().add(gandalf);
            elf.getHeroes().add(legolas);
            dwarf.getHeroes().add(gimli);
        }
    }
}
