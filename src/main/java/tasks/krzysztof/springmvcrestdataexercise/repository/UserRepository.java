package tasks.krzysztof.springmvcrestdataexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tasks.krzysztof.springmvcrestdataexercise.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
    Optional<User> findById(String id);
    Optional<User> findByUsername(String username);

    User save(User user);
//    List<User> saveAll(List<User> userList);
}
