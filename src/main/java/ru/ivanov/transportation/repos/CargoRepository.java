package ru.ivanov.transportation.repos;

import org.springframework.data.repository.CrudRepository;
import ru.ivanov.transportation.domain.Cargo;

public interface CargoRepository
         extends CrudRepository<Cargo, String> {
}