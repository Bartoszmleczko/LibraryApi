package pl.mleczkobartosz.LibraryApi.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mleczkobartosz.LibraryApi.Entity.Role;
import pl.mleczkobartosz.LibraryApi.Entity.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findUserByUsername(username);
        List<GrantedAuthority> roles = getUserAuthority(user.getRole());
        return buildUserForAuthentication(user,roles);
    }


    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (Role role : userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities){
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                true,true,true,true,authorities);
    }


}
