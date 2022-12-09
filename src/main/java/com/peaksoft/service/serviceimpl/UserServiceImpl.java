package com.peaksoft.service.serviceimpl;

import com.peaksoft.converter.user.UserConverterRequest;
import com.peaksoft.converter.user.UserConverterResponse;
import com.peaksoft.dto.request.UserRequest;
import com.peaksoft.dto.response.UserResponse;
import com.peaksoft.entity.Role;
import com.peaksoft.entity.User;
import com.peaksoft.repository.RoleRepository;
import com.peaksoft.repository.UserRepository;
import com.peaksoft.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserConverterResponse userConverterResponse;

    private final UserConverterRequest userConverterRequest;

    private final RoleRepository roleRepository;



    @Override
    public UserResponse create(UserRequest userRequest) {
        User user = mapToEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);
        return mapToResponse(user);
    }

    @Override
    public List<UserResponse> getAllUser() {
       return userConverterResponse.getAll(userRepository.findAll());
    }

    @Override
    public UserResponse deleteUserById(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return userConverterResponse.create(user);
    }

    @Override
    public UserResponse updateUser(Long id,UserRequest userRequest) {
        User user = userRepository.findById(id).get();
        userConverterRequest.update(user,userRequest);
        return userConverterResponse.create(userRepository.save(user));
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userConverterResponse.create(userRepository.findById(id).get());
    }





    private User mapToEntity(UserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setLastName(request.getLastName());
        user.setFirsName(request.getFirstName());
        user.setPassword(request.getPassword());
        return user;
    }

    private UserResponse mapToResponse(User user) {
        if (user == null) {
            return null;
        }
        UserResponse response = new UserResponse();
        if (user.getId() != null) {
            response.setId(String.valueOf(user.getId()));
        }
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirsName());
        return response;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("not found email"));
    }

//
//    @PostConstruct
//    public void initMethod(){
//
//        Role role1 = new Role("mentor");
//        Role role2 = new Role("instructor");
//        Role role3 = new Role("admin");
//
//        repository.save(role1);
//        repository.save(role2);
//        repository.save(role3);
//
//        User mentor1 = new User("Muhammed","Batyrov","Batyrov@gmal.com","mentor", List.of(role1));
//        User mentor2 = new User("Ulan","Kubanychbekov","Kubanychbekov@gmal.com","mentor", List.of(role1));
//        User mentor3 = new User("Muhammed","Allanov","Allanov@gmal.com","instructor", Arrays.asList(role2,role1));
//        User mentor4 = new User("Esen","Niyazov","Niyazov@gmal.com","mentor", Arrays.asList(role3,role2,role1));
//
//        userRepository.save(mentor1);
//        userRepository.save(mentor2);
//        userRepository.save(mentor3);
//        userRepository.save(mentor4);
//
//    }

//    @PostConstruct
//    public void initMethod(){
//        Role role1 = new Role();
//        role1.setRoleName("Admin");
//
//        Role role2 = new Role();
//        role2.setRoleName("Instructor");
//
//        Role role3 = new Role();
//        role3.setRoleName("Student");
//
//        UserRequest request = new UserRequest();
//        request.setEmail("esen@gmail.com");
//        request.setLastName("Niyszov");
//        request.setPassword(passwordEncoder.encode("1234"));
//        request.setFirstName("Esen");
//
//
//        UserRequest request1 = new UserRequest();
//        request1.setEmail("allanov@gmail.com");
//        request1.setLastName("Allanov");
//        request1.setPassword(passwordEncoder.encode("1234"));
//        request1.setFirstName("Muhammed");
//
//        User user2 = mapToEntity(request);
//
//        user2.setRoles(Arrays.asList(role1));
//        role1.setUsers(Arrays.asList(user2));
//
//
//        User user3 = mapToEntity(request1);
//
//        user3.setRoles(Arrays.asList(role2));
//        role2.setUsers(Arrays.asList(user3));
//
//        userRepository.save(user2);
//        userRepository.save(user3);
//        roleRepository.save(role1);
//        roleRepository.save(role2);
//        roleRepository.save(role3);
//    }

    @PostConstruct
    public void initMethod() {
        userRepository.findByEmail(admin().getEmail())
                .ifPresent(userRepository::delete);
        userRepository.save(admin());
        userRepository.findByEmail(instructor().getEmail())
                .ifPresent(userRepository::delete);
        userRepository.save(instructor());
    }

    public User admin() {
        Role role = new Role();
        role.setRoleName("Admin");
        User user = new User();
        user.setEmail("esen@gmail.com");
        user.setPassword("esen");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFirsName("Esen");
        user.setLastName("Niyazov");
        user.addRole(role);
        return user;
    }

    public User instructor() {
        Role role = new Role();
        role.setRoleName("Instructor");
        User user = new User();
        user.setEmail("allanov@gmail.com");
        user.setPassword("allanov");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFirsName("Muhammed");
        user.setLastName("Batyrov");
        user.addRole(role);
        return user;
    }
}
