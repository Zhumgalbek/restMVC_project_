package com.peaksoft.converter.user;

import com.peaksoft.dto.response.TaskResponse;
import com.peaksoft.dto.response.UserResponse;
import com.peaksoft.entity.Task;
import com.peaksoft.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverterResponse {

    public UserResponse create(User user) {
        if (user == null) return null;
        UserResponse userResponse = new UserResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setLastName(user.getLastName());
        userResponse.setFirstName(user.getFirsName());
        userResponse.setEmail(user.getEmail());

        return userResponse;
    }

    public List<UserResponse> getAll(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(create(user));
        }
        return userResponses;
    }
}
