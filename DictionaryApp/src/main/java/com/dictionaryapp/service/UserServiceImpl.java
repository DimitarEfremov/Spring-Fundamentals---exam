package com.dictionaryapp.service;

import com.dictionaryapp.model.DTOs.UserLoginDTO;
import com.dictionaryapp.model.DTOs.UserRegisterDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, LoggedUser loggedUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }

        boolean userNameOrPassExist = userRepository.existsByUserNameAndEmail(userRegisterDTO.getUsername(), userRegisterDTO.getEmail());

        if (userNameOrPassExist){
            return false;
        }

        User user = new User();
        user.setUserName(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        userRepository.save(user);

        return true;

    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        User user = userRepository.findByUserName(username);

        if (user != null
                && passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            loggedUser.login(username, user.getId());
            return true;
        }

        return false;


    }

    @Override
    public void logout() {
        loggedUser.logout();
    }
}
