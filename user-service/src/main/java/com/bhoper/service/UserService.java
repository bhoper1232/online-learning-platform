package com.bhoper.service;

import com.bhoper.dto.UserCreateRequest;
import com.bhoper.model.User;
import com.bhoper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(UserCreateRequest createRequest) {
        User user = User.builder()
                .username(createRequest.username())
                .email(createRequest.email())
                .firstName(createRequest.firstName())
                .lastName(createRequest.lastName())
                .password(BCrypt.hashpw(createRequest.password(), BCrypt.gensalt()))
                .roles(createRequest.roles())
                .birthDate(createRequest.birtDate())
                .build();
        return userRepository.save(user);
    }

}
