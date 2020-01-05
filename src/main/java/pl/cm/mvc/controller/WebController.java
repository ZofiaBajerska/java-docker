package pl.cm.mvc.controller;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pl.cm.mvc.dto.User;
import pl.cm.mvc.service.UserService;

@RestController
public class WebController {

  final UserService userService;

  public WebController(final UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user/list")
  public List<User> getUsers() {
    return userService.getAllUsers();
  }


//  @ExceptionHandler(HttpClientErrorException.class)
//  public ResponseEntity onError(HttpClientErrorException exception) {
//    return ResponseEntity.status(exception.getStatusCode()).build();
//  }

  @PostMapping(value = { "/user/add", "/user" })
  public User addUser(@RequestBody User user) {
    if (addIfNotExists(user)) {
      return new User(userService.addUser(user).getId());
    }

    throw new ResponseStatusException(HttpStatus.CONFLICT);
    //  throw new HttpClientErrorException(HttpStatus.CONFLICT);
  }

  @PutMapping(value = { "/user/{userId}" })
  public User updateUser(@RequestBody User user, @PathVariable("userId") Long userId) {
    final Optional<User> userById = userService.findById(userId);
    if (userById.isPresent()) {
      User userFromDb = userById.get();
      updateIfNonNull(user::getUsername, userFromDb::setUsername);
      updateIfNonNull(user::getFirstname, userFromDb::setFirstname);
      updateIfNonNull(user::getLastname, userFromDb::setLastname);
      updateIfNonNull(user::getAge, userFromDb::setAge);
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }

  private <T> void updateIfNonNull(Supplier<? extends T> supplier, Consumer<T> consumer) {
    if (Objects.nonNull(supplier.get())) {
      consumer.accept(supplier.get());
    }
  }


  @PostMapping(value = { "/user/addAll" })
  public List<User> addAll(@RequestBody List<User> users) {
    List<User> result = new ArrayList<>();
    for (User user : users) {
      final Optional<User> userByName = userService.findUserByName(user.getUsername());
      if (userByName.isEmpty()) {
        final User newUser = userService.addUser(user);
        result.add(new User(newUser.getId(), newUser.getUsername()));
      }
    }
    return result;
  }

  @DeleteMapping("/user/{userId}")
  public void delete(@PathVariable("userId") Long userId) {
    final Optional<User> byId = userService.findById(userId);
    if (byId.isPresent()) {
      userService.delete(byId.get());
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/user/search")
  public List<User> search(User user) {
    Example example = Example.of(user, ExampleMatcher.matchingAny());
    return userService.findByExample(example);
  }

  @GetMapping("/user/delete")
  @ResponseStatus(HttpStatus.OK)
  public List<Long> delete(User user) {
    Example example = Example.of(user, ExampleMatcher.matchingAny());
    final List<User> byExample = userService.findByExample(example);
    final List<Long> removed = byExample.stream()
        .map(userService::delete)
        .filter(Objects::nonNull)
        .collect(toList());

    if (removed.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return removed;
  }

  private boolean addIfNotExists(@RequestBody final User user) {
    final Optional<User> userByName = userService.findUserByName(user.getUsername());
    if (userByName.isEmpty()) {
      return true;
    }
    return false;
  }
}
