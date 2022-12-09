package com.peaksoft.controller;


import com.peaksoft.converter.login.LoginConverter;
import com.peaksoft.dto.request.UserRequest;
import com.peaksoft.dto.response.LoginResponse;
import com.peaksoft.dto.response.UserResponse;
import com.peaksoft.entity.User;
import com.peaksoft.repository.UserRepository;
import com.peaksoft.security.ValidationExceptionType;
import com.peaksoft.security.jwt.JwtTokenUtil;
import com.peaksoft.service.serviceimpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt/")
public class AuthApi {

    private final UserServiceImpl userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final LoginConverter loginConverter;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody UserRequest request) {
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.getEmail(),
                            request.getPassword());
            User user = userRepository.findByEmail(token.getName()).get();
            return ResponseEntity.ok().body(loginConverter.
                    loginView(jwtTokenUtil.generateToken(user),
                            ValidationExceptionType.SUCCESSFUL, user));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                    body(loginConverter.
                            loginView("", ValidationExceptionType
                                    .LOGIN_FAILED, null));
        }
    }

    @PostMapping("registration")
    public UserResponse create(@RequestBody UserRequest request) {
        for (int i = 0; i < userRepository.findAll().size(); i++) {
            if (!Objects.equals(userRepository.findAll().get(i).getEmail(), request.getEmail())){
                return userService.create(request);
            }
        }
        return null;
    }


    @GetMapping("/getUser")
    public List<UserResponse> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/getUserById/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/updateUser/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/deleteUser/{id}")
    public UserResponse deleteUser(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }
}
