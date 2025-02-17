package com.example.demo.services;

import com.example.demo.aspects.TrackUserAction;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    @TrackUserAction
    public void saveUser(User user){
        userRepository.save(user);
    }

    @TrackUserAction
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @TrackUserAction
    public void updateUser(User user) {
        userRepository.update(user);
    }
}
