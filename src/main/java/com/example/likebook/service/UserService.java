package com.example.likebook.service;

import com.example.likebook.model.User;
import com.example.likebook.model.dto.UserLoginDTO;
import com.example.likebook.model.dto.UserRegisterDTO;
import com.example.likebook.repository.UserRepo;
import com.example.likebook.session.LoggedUserSession;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUserSession loggedUserSession;
    private final HttpSession session;

    @Autowired
    public UserService(UserRepo userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder,
                       LoggedUserSession loggedUserSession, HttpSession session) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUserSession = loggedUserSession;
        this.session = session;
    }

    public User getUserById(long id) {
        return this.userRepo.findUserById(id);
    }

    public User register(UserRegisterDTO userRegisterDTO) {
        User newUser = modelMapper.map(userRegisterDTO, User.class);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return this.userRepo.save(newUser);
    }

    public User login(UserLoginDTO userLoginDTO) {
        User user = this.userRepo.findUserByUsername(userLoginDTO.getUsername()).orElse(null);

        if (user != null) {
            loggedUserSession.setId(user.getId());
            loggedUserSession.setUsername(user.getUsername());
            loggedUserSession.setEmail(user.getEmail());
        }

        return user;
    }

    public boolean checkUserData(UserLoginDTO userLoginDTO) {
        User user = this.userRepo.findUserByUsername(userLoginDTO.getUsername()).orElse(null);

        if (user == null) {
            return false;
        }

        return this.passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword());
    }

    public User userNameIsTaken(String name) {
        return this.userRepo.findUserByUsername(name).orElse(null);
    }

    public User emailIsTaken(String email) {
        return this.userRepo.findUserByEmail(email).orElse(null);
    }


    public void logout() {
        this.loggedUserSession.setId(0);
        this.loggedUserSession.setUsername(null);
        this.loggedUserSession.setEmail(null);
        this.session.invalidate();
    }

    public void initUsersDB() {
        if (userRepo.count() == 0) {
            List<User> users = new ArrayList<>();

            User user = new User()
                    .setUsername("Nqma")
                    .setPassword(passwordEncoder.encode("000000"))
                    .setEmail("nqma@gmail.com");

            User user2 = new User()
                    .setUsername("Nqkoi si")
                    .setPassword(passwordEncoder.encode("121212"))
                    .setEmail("nqkoi@gmail.com");

            User user3 = new User()
                    .setUsername("Nishto")
                    .setPassword(passwordEncoder.encode("121212"))
                    .setEmail("nishto@gmail.com");

            users.add(user);
            users.add(user2);
            users.add(user3);

            this.userRepo.saveAll(users);
        }
    }
}
