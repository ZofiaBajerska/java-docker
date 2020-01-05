package pl.cm.mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import pl.cm.mvc.dto.User;
import pl.cm.mvc.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> findUserByName(String userName) {
    return userRepository.findByUsername(userName);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User addUser(User user) {
    return userRepository.save(user);
  }

  public Optional<User> findById(final Long userId) {
    return userRepository.findById(userId);
  }

  public List<User> findByExample(final Example example) {
    return userRepository.findAll(example);
  }

  public Long delete(final User user) {
    return userRepository.removeById(user.getId());
  }
}
