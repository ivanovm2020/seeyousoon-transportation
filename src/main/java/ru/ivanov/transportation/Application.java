package ru.ivanov.transportation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ru.ivanov.transportation.domain.Cargo;
import ru.ivanov.transportation.domain.Cargo.Type;
import ru.ivanov.transportation.repos.CargoRepository;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner dataLoader(CargoRepository repo) {
    return args -> {
      repo.save(new Cargo("FORD", "Ford", Type.CAR));
      repo.save(new Cargo("TOYA", "Toyota", Type.CAR));
      repo.save(new Cargo("HNDA", "Honda", Type.BIKE));
      repo.save(new Cargo("SZKI", "Suzuki", Type.BIKE));
      repo.save(new Cargo("CLST", "Closet", Type.FURNITURE));
      repo.save(new Cargo("DESK", "Desk", Type.FURNITURE));
      repo.save(new Cargo("CLTS", "Clothes", Type.THING));
      repo.save(new Cargo("APLS", "Appliances", Type.THING));
      repo.save(new Cargo("METL", "Metal", Type.SCRAP));
      repo.save(new Cargo("WOOD", "Wood", Type.SCRAP));
    };
  }
}
