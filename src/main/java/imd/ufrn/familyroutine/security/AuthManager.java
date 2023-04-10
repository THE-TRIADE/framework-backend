package imd.ufrn.familyroutine.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import imd.ufrn.familyroutine.service.exception.InvalidUserException;

@Component
public class AuthManager implements AuthenticationManager {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserDetails user = userDetailsServiceImpl.loadUserByUsername(username);
        Boolean validPW = BCrypt.checkpw(password, user.getPassword().replace("{bcrypt}", ""));

        if(validPW) {
            return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        }
        else {
            throw new BadCredentialsException(new InvalidUserException().getMessage());
        }
    }
    
}
