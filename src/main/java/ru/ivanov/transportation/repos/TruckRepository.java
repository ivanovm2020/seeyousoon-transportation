package ru.ivanov.transportation.repos;

import org.springframework.data.repository.CrudRepository;

import ru.ivanov.transportation.domain.Truck;

public interface TruckRepository
         extends CrudRepository<Truck, Long> {
}
