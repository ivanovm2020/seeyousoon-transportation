package ru.ivanov.transportation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ivanov.transportation.domain.Cargo;
import ru.ivanov.transportation.repos.CargoRepository;

import java.util.List;

@RestController
public class CargoRestController {
    private final CargoRepository cargoRepo;;

    @Autowired
    public CargoRestController(CargoRepository cargoRepo) {
        this.cargoRepo = cargoRepo;
    }

    @RequestMapping(value = "/api/cargos", method = RequestMethod.GET)
    @ResponseBody
    public List<Cargo> findAllCargo() {
        return (List<Cargo>) cargoRepo.findAll();
    }
}