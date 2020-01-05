package pl.cm.mvc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import pl.cm.mvc.dto.User;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String userName);

  @Modifying
  @Transactional
  Long removeById(Long id);
}
