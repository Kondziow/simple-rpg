package wojtanowski.konrad.simple_rpg.initializr;

import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wojtanowski.konrad.simple_rpg.hero.entity.Hero;
import wojtanowski.konrad.simple_rpg.hero.service.api.HeroService;
import wojtanowski.konrad.simple_rpg.species.entity.Species;
import wojtanowski.konrad.simple_rpg.species.service.api.SpeciesService;
import jakarta.validation.Validator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final SpeciesService speciesService;
    private final HeroService heroService;
    private final Validator validator;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome to Simple RPG!");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                showMenu();
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1" -> printSpecies();
                    case "2" -> printHeroes();
                    case "3" -> addHero(scanner);
                    case "4" -> deleteHero(scanner);
                    case "5" -> printHeroByName(scanner);
                    case "6" -> printHeroByNameAndServesDarkness(scanner);
                    case "7" -> running = false;
                    default -> System.out.println("Invalid choice, try again");
                }
            }
        }
        System.out.println("Farewell, traveler!");
    }

    private void showMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Show all species");
        System.out.println("2. Show all heroes");
        System.out.println("3. Add hero");
        System.out.println("4. Delete hero");
        System.out.println("5. Print hero by name");
        System.out.println("6. Print hero by name and serves darkness");
        System.out.println("7. Exit");
        System.out.println("Choice: ");
    }

    private void printSpecies() {
        List<Species> species = speciesService.findAll();
        if (species.isEmpty()) {
            System.out.println("No species found");
        } else {
            for (int i = 0; i < species.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, species.get(i));
            }
        }

    }

    private void printHeroes() {
        List<Hero> heroes = heroService.findAll();
        if (heroes.isEmpty()) {
            System.out.println("No heroes found");
        } else {
            for (int i = 0; i < heroes.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, heroes.get(i));
            }
        }
    }

    public void addHero(Scanner scanner) {
        System.out.println("Enter hero name:");
        String name = scanner.nextLine();

        System.out.println("Does the hero serve darkness? (true/false)");
        boolean servesDarkness = Boolean.parseBoolean(scanner.nextLine());

        System.out.println("Choose hero species:");
        List<Species> speciesList = speciesService.findAll();
        printSpecies();

        int speciesIndex = getValidIndex(scanner, speciesList.size());
        if (speciesIndex == -1) return;

        Species selectedSpecies = speciesList.get(speciesIndex);

        Hero hero = Hero.builder()
                .id(UUID.randomUUID())
                .name(name)
                .servesDarkness(servesDarkness)
                .species(selectedSpecies)
                .build();

        Set<ConstraintViolation<Hero>> violations = validator.validate(hero);

        if (!violations.isEmpty()) {
            System.out.println("!!! Hero is invalid and cannot be saved !!!");
            violations.forEach(v -> {
                System.out.printf(" - Field '%s': '%s%n'", v.getPropertyPath(), v.getMessage());
            });
            return;
        }

        heroService.save(hero);
        System.out.println("Hero added successfully!");
    }

    private void deleteHero(Scanner scanner) {
        List<Hero> heroes = heroService.findAll();
        if (heroes.isEmpty()) {
            System.out.println("Nothing to delete.");
            return;
        }

        System.out.println("Choose hero to delete:");
        printHeroes();

        int heroIndex= getValidIndex(scanner, heroes.size());
        if (heroIndex == -1) return;

        UUID id = heroes.get(heroIndex).getId();
        heroService.deleteById(id);
        System.out.println("Hero deleted.");
    }

    private void printHeroByName(Scanner scanner) {
        System.out.println("Enter hero name:");
        String name = scanner.nextLine();

        List<Hero> heroes = heroService.findByName(name);
        heroes.forEach(h -> System.out.printf("%d. %s%n", heroes.indexOf(h) + 1,h));
    }

    private void printHeroByNameAndServesDarkness(Scanner scanner) {
        System.out.println("Enter hero name:");
        String name = scanner.nextLine();

        System.out.println("Enter hero serves darkness (true/false):");
        boolean servesDarkness = Boolean.parseBoolean(scanner.nextLine());

        List<Hero> heroes = heroService.findByNameAndServesDarkness(name, servesDarkness);
        heroes.forEach(h -> System.out.printf("%d. %s%n", heroes.indexOf(h) + 1,h));
    }

    private int getValidIndex(Scanner scanner, int listSize) {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > listSize) {
                System.out.println("Index out of bounds.");
                return -1;
            }
            return choice - 1;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return -1;
        }
    }
}
