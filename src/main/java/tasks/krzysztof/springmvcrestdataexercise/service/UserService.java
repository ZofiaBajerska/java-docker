package tasks.krzysztof.springmvcrestdataexercise.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import tasks.krzysztof.springmvcrestdataexercise.dto.User;
import tasks.krzysztof.springmvcrestdataexercise.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUser(User user){
        return userRepository.findAll((Example.of(user)));
    }

    public List<User> findUserMatching(User user){
        return userRepository.findAll((Example.of(user, ExampleMatcher.matchingAny())));
    }
}
