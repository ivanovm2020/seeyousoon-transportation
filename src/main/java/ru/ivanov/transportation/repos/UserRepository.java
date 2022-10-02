package ru.ivanov.transportation.repos;
import org.springframework.data.repository.CrudRepository;
import ru.ivanov.transportation.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
}
