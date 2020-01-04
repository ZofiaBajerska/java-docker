package tasks.krzysztof.springmvcrestdataexercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;
import tasks.krzysztof.springmvcrestdataexercise.dto.User;
import tasks.krzysztof.springmvcrestdataexercise.repository.UserRepository;
import tasks.krzysztof.springmvcrestdataexercise.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class WebController {

    UserRepository userRepository;

    UserService userService;

    @Autowired
    public WebController(UserRepository userRepository, UserService userService) {

        this.userRepository = userRepository;
        this.userService = userService;
    }

//    @GetMapping("/user/list")
//    public List<User> index(){
//        List<User> userList = new ArrayList<>();
//
//        userList = userRepository.findAll().stream().collect(Collectors.toList());
//        return userList;
//    }
//    @PostMapping("/user/add")
//    @ResponseStatus(HttpStatus.CREATED)
//    public User addUser(@RequestBody User user){
//        userRepository.save(user);
//        return userRepository.findById(user.getId()).get();
//    }
//
//    @PostMapping("/user/addAll")
//    @ResponseStatus(HttpStatus.CREATED)
//    public List<User> addUser(@RequestBody List<User> userList){
//        userRepository.saveAll(userList);
//        return userRepository.findAll();
//    }


////zadanie10/////////////////start
//    @PutMapping("/user/{userId}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void putUser(@PathVariable String userId, User user){
//        if(!userRepository.findById(userId).isPresent()) {
//
//        }
//    }

//    @PutMapping(value = "/user/{userId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
//    public User putWeapons(@PathVariable("userId") Long id, @RequestBody User user) {
//        if (user.getId() == null || !user.getId().equals(id)) {
//            throw new IllegalArgumentException("bad request");
//        }
//        Optional<User> checkedUser = userRepository.findById(id);
//        if (checkedUser.isEmpty()) {
//            throw new IllegalArgumentException("not found");
//        }
//        userRepository.save(user);
//        return user;
//    }

    @ExceptionHandler({IllegalArgumentException.class, Exception.class})
    public ResponseEntity handleError(HttpServletRequest req, Exception ex) {
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    ////zadanie10/////////////////koniec

    /////zadanie11 i 12////przyklad kiedy chcemy tylko po danych polach wyszukiwac
    @GetMapping("/users/search")
    public List<User> findBy(@RequestParam(name = "firstname") String firrstname, @RequestParam(name="lastname") String lastnmame){
        User user= new User();
        user.setFirstname(firrstname);
        user.setLastname(lastnmame);
        List<User> userList = new ArrayList<>();

        userList = userService.findUserMatching(user);
        return userList;
    }

    /////zadanie11 i 12////przyklad kiedy chcemy wyszukiwac po calym obiekcie
    ///niebezpieczenstwo ze mozemy dac uzytkownikowi za duzo wladzy
//    @GetMapping("/user/search")
//    public List<User> findBy(User user){
//        List<User> userList = new ArrayList<>();
//
//        userList = userService.findUserMatching(user);
//        return userList;
//    }

    ///koniec11 i 12 zadania/////



}
