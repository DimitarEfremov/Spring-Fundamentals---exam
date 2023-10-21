package com.dictionaryapp.service;

import com.dictionaryapp.model.DTOs.UserLoginDTO;
import com.dictionaryapp.model.DTOs.UserRegisterDTO;

public interface UserService {
    boolean register(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();
}
