package com.ace.authorizereg_loginpage.registration;

import com.ace.authorizereg_loginpage.appuser.AppUser;
import com.ace.authorizereg_loginpage.appuser.AppUserRole;
import com.ace.authorizereg_loginpage.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private EmailValidator emailValidator;
    public String register(RegistrationRequest request) {
    boolean isValidEmail = emailValidator.test(request.getEmail());
    if(!isValidEmail){
        throw new EmailNotValidException("Email Not Valid");
    }
        return appUserService.signUpUser(
                new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                        AppUserRole.USER
        ));
    }


}
