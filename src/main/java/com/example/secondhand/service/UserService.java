package com.example.secondhand.service;

import com.example.secondhand.dto.CreateUserRequest;
import com.example.secondhand.dto.UpdateUserRequest;
import com.example.secondhand.dto.UserDto;
import com.example.secondhand.dto.UserDtoConverter;
import com.example.secondhand.exception.EmailAlreadyExistException;
import com.example.secondhand.exception.UserNotFoundException;
import com.example.secondhand.model.User;
import com.example.secondhand.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter converter;

    public UserService(UserRepository userRepository, UserDtoConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public UserDto getUserByMail(String mail) {
        return converter.convert(findUserByMail(mail));
    }

    public UserDto createUser(CreateUserRequest request) {
        if(userRepository.existsByMail(request.getMail())) {
            throw new EmailAlreadyExistException("Email is already used");
        }
        User user = new User(
                null,
                request.getMail(),
                request.getFirstName(),
                request.getMiddleName(),
                request.getLastName()
        );
        return converter.convert(userRepository.save(user));
    }
    public UserDto updateUser(String mail, UpdateUserRequest request) {
        User user = findUserByMail(mail);
        User updatedUser = new User(
                user.getId(),
                request.getMail(),
                request.getFirstName(),
                request.getMiddleName(),
                request.getLastName()
        );
        return converter.convert(userRepository.save(updatedUser));
    }

    protected User findUserByMail(String mail) {
        return userRepository.findByMail(mail)
                .orElseThrow(() -> new UserNotFoundException("Requested user not found with mail " + mail));
    }

}
