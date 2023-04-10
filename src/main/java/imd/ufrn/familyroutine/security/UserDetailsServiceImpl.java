package imd.ufrn.familyroutine.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import imd.ufrn.familyroutine.repository.GuardianRepository;
import imd.ufrn.familyroutine.service.exception.InvalidUserException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private GuardianRepository guardianRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.guardianRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(new InvalidUserException().getMessage()));
    }
    
}
