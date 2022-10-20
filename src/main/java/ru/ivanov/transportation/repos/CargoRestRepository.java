package ru.ivanov.transportation.repos;

import org.springframework.data.repository.CrudRepository;
import ru.ivanov.transportation.domain.Cargo;

import java.util.List;

public interface CargoRestRepository extends CrudRepository<Cargo, String> {

    @Override
    List<Cargo> findAll();
}