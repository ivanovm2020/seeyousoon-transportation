package ru.ivanov.transportation.repos;

import org.springframework.data.repository.CrudRepository;

import ru.ivanov.transportation.domain.Order;

public interface OrderRepository 
         extends CrudRepository<Order, Long> {
}
