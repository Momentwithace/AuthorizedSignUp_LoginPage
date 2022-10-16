package com.ace.authorizereg_loginpage.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found ";

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser){
        boolean userExists = appUserRepository.findByEmail(
                appUser.getEmail()).isPresent();

        if(userExists){
            throw new UserAlreadyExistsException(
                    String.format("User with email %s already exists"));
        }
        String encodedPassword = bCryptPasswordEncoder.encode(
                appUser.getPassword());
        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);
        return "it works";
    }
}