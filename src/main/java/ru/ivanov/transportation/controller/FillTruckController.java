package ru.ivanov.transportation.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import ru.ivanov.transportation.domain.Cargo;
import ru.ivanov.transportation.domain.Cargo.Type;
import ru.ivanov.transportation.domain.Order;
import ru.ivanov.transportation.domain.Truck;
import ru.ivanov.transportation.domain.User;
import ru.ivanov.transportation.repos.CargoRepository;
import ru.ivanov.transportation.repos.TruckRepository;
import ru.ivanov.transportation.repos.UserRepository;

@Controller
@RequestMapping("/fill")
@SessionAttributes("order")
@Slf4j
public class FillTruckController {

  private final CargoRepository cargoRepo;
  private TruckRepository truckRepo;
  private UserRepository userRepo;

  @Autowired
  public FillTruckController(
        CargoRepository cargoRepo,
        TruckRepository truckRepo,
        UserRepository userRepo) {
    this.cargoRepo = cargoRepo;
    this.truckRepo = truckRepo;
    this.userRepo = userRepo;
  }

  @ModelAttribute
  public void addCargosToModel(Model model) {
    List<Cargo> cargos = new ArrayList<>();
    cargoRepo.findAll().forEach(i -> cargos.add(i));

    Type[] types = Cargo.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          filterByType(cargos, type));
    }
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }

  @ModelAttribute(name = "truck")
  public Truck truck() {
    return new Truck();
  }

  @ModelAttribute(name = "user")
  public User user(Principal principal) {
	    String username = principal.getName();
	    User user = userRepo.findByUsername(username);
	    return user;
  }

  @GetMapping
  public String showFillForm() {
    return "fill";
  }

  @PostMapping
  public String processTruck(
          @Valid Truck truck, Errors errors,
          @ModelAttribute Order order) {

    log.info("   --- Saving truck");

    if (errors.hasErrors()) {
      return "fill";
    }

    Truck saved = truckRepo.save(truck);
    order.addTruck(saved);

    return "redirect:/orders/current";
  }

  private List<Cargo> filterByType(
          List<Cargo> cargos, Type type) {
    return cargos
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }
}
