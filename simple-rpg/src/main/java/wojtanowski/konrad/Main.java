package wojtanowski.konrad;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static void main(String[] args) {
        Map<String, Species> speciesMap = createSpecies();
        List<Hero> heroes = createHeroes(speciesMap);
        linkHeroesToSpecies(heroes);

        System.out.println("=== Full Registry: Species and Their Heroes ===");
        speciesMap.values().stream().toList()
                .forEach(species -> {
                    System.out.println(species);
                    species.getHeroes().forEach(hero -> {
                        System.out.println("\t" + hero);
                    });
                });

        System.out.println("\n=== Unique Heroes Collection ===");
        Set<Hero> heroSet = speciesMap.values().stream()
                .map(Species::getHeroes)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        heroSet.forEach(System.out::println);

        System.out.println("\n=== Filtered View: Servants of Darkness ===");
        List<Hero> darkServants = heroes.stream()
                .filter(Hero::isServesDarkness)
                .sorted()
                .toList();

        if (darkServants.isEmpty()) {
            System.out.println("No dark servants found - the Fellowship stands united!");
        } else {
            darkServants.forEach(System.out::println);
        }

        System.out.println("\n=== Heroes Data Transfer Objects (Sorted by Name) ===");
        List<HeroDto> heroesDto = heroes.stream()
                .map(hero -> new HeroDto(hero.getName(), hero.isServesDarkness(), hero.getSpecies().getName()))
                .sorted(Comparator.comparing(HeroDto::getName))
                .toList();

        heroesDto.forEach(System.out::println);

        System.out.println("\n=== Binary Data Persistence: Serialization and Restoration ===");
        String filePath = "species.bin";
        try (var fileOutputStream = new FileOutputStream(filePath);
             var objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(speciesMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, Species> serializedSpecies;

        try (var fileInputStream = new FileInputStream(filePath);
             var objectInputStream = new java.io.ObjectInputStream(fileInputStream)) {
            serializedSpecies = (Map<String, Species>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        serializedSpecies.values().stream().toList()
                .forEach(species -> {
                    System.out.println(species);
                    species.getHeroes().forEach(hero -> {
                        System.out.println("\t" + hero);
                    });
                });

        System.out.println("\n=== Parallel Processing Benchmark: Multi-threaded Execution Analysis ===");
        List<Species> speciesList = speciesMap.values().stream().toList();

        for (int threads = 1; threads <= 4; threads++) {
            System.out.println("Testing with " + threads + " thread(s)...");
            long startTime = System.currentTimeMillis();

            try (ForkJoinPool customThreadPool = new ForkJoinPool(threads)) {
                customThreadPool.submit(() ->
                        speciesList.parallelStream().forEach(species -> {
                            String threadName = Thread.currentThread().getName();
                            System.out.printf("[%s] Processing species: %s (Heroes: %d)%n",
                                    threadName, species.getName(), species.getHeroes().size());

                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        })
                ).get();
            } catch (Exception e) {
                System.err.println("Error during parallel processing: " + e.getMessage());
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Execution time: " + (endTime - startTime) + "ms\n");
        }
    }

    private static Map<String, Species> createSpecies() {
        var speciesNames = List.of("Men", "Hobbits", "Elves", "Dwarves");
        var speciesBaseHealth = List.of(100, 50, 150, 120);

        return IntStream.range(0, speciesNames.size())
                .mapToObj(i -> Species.builder()
                        .name(speciesNames.get(i))
                        .baseHealth(speciesBaseHealth.get(i))
                        .heroes(new ArrayList<>())
                        .build())
                .collect(Collectors.toMap(Species::getName, species -> species));
    }

    private static List<Hero> createHeroes(Map<String, Species> speciesMap) {
        List<Hero> heroes = new ArrayList<>();

        addHero(heroes, "Aragorn", false, speciesMap.get("Men"));
        addHero(heroes, "Gimli", false, speciesMap.get("Dwarves"));
        addHero(heroes, "Boromir", false, speciesMap.get("Men"));
        addHero(heroes, "Frodo", false, speciesMap.get("Hobbits"));
        addHero(heroes, "Samwise", false, speciesMap.get("Hobbits"));
        addHero(heroes, "Merry", false, speciesMap.get("Hobbits"));
        addHero(heroes, "Pippin", false, speciesMap.get("Hobbits"));
        addHero(heroes, "Legolas", false, speciesMap.get("Elves"));
        addHero(heroes, "Gandalf", false, speciesMap.get("Men"));

        return heroes;
    }

    private static void addHero(List<Hero> heroes, String name, boolean servesDarkness, Species species) {
        heroes.add(Hero.builder().name(name).servesDarkness(servesDarkness).species(species).build());
    }

    private static void linkHeroesToSpecies(List<Hero> heroes) {
        heroes.forEach(hero -> {
            if (hero.getSpecies() != null) {
                hero.getSpecies().getHeroes().add(hero);
            }
        });
    }
}
