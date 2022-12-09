package com.peaksoft.service;

import com.peaksoft.dto.request.UserRequest;
import com.peaksoft.dto.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserResponse create(UserRequest userRequest);

    List<UserResponse> getAllUser();

    UserResponse deleteUserById(Long id);

    UserResponse updateUser(Long id,UserRequest userRequest);

    UserResponse getUserById(Long id);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;


}
