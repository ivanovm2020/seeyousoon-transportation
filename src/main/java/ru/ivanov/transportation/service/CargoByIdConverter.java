package ru.ivanov.transportation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.ivanov.transportation.domain.Cargo;
import ru.ivanov.transportation.repos.CargoRepository;

@Component
public class CargoByIdConverter implements Converter<String, Cargo> {

  private CargoRepository cargoRepo;

  @Autowired
  public CargoByIdConverter(CargoRepository cargoRepo) {
    this.cargoRepo = cargoRepo;
  }

  @Override
  public Cargo convert(String id) {
    return cargoRepo.findById(id).orElse(null);
  }

}
