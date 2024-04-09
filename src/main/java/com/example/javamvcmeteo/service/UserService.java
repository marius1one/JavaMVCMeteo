/*package com.example.javamvcmeteo.service;

import com.example.javamvcmeteo.models.UserModel;
import com.example.javamvcmeteo.repository.UserRepository;
import com.example.javamvcmeteo.validation.ValidationRules;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
   private ValidationRules validationRules;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel registruotiVartotoja(String login, String password) {
        if(validationRules.isValid(password) && login != null) {
            UserModel userModel = new UserModel();
            userModel.setLogin(login);
            userModel.setPassword(password);
           return userRepository.save(userModel);
        } else {
            return null;
        }

    }

    public UserModel autentifikacija(String login, String passwrod) {
        return userRepository.findByLoginAndPassword(login, passwrod).orElse(null);
    }
}
*/