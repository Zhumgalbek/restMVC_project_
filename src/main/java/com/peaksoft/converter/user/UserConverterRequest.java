package com.peaksoft.converter.user;

import com.peaksoft.dto.request.UserRequest;
import com.peaksoft.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverterRequest {

    public User create(UserRequest userRequest) {
        if (userRequest == null) return null;
        User user = new User();
        user.setFirsName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        return user;
    }


    public void update(User user, UserRequest userRequest) {
        if (userRequest.getFirstName() != null) {
            user.setFirsName(userRequest.getFirstName());
        } if (userRequest.getLastName() != null) {
            user.setLastName(userRequest.getLastName());
        } if (userRequest.getPassword() != null) {
            user.setPassword(userRequest.getPassword());
        } if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }

    }
}
