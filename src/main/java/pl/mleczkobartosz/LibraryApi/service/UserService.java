package pl.mleczkobartosz.LibraryApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mleczkobartosz.LibraryApi.Entity.Role;
import pl.mleczkobartosz.LibraryApi.Entity.User;
import pl.mleczkobartosz.LibraryApi.repository.RoleRepository;
import pl.mleczkobartosz.LibraryApi.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

        private final UserRepository userRepository;
        private final RoleRepository roleRepository;
        private final BCryptPasswordEncoder bCryptPasswordEncoder;

        @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user){

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role roleUser = roleRepository.findByName("USER");
        user.setRole(new HashSet<Role>(Arrays.asList(roleUser)));
        return userRepository.save(user);

    }

}
