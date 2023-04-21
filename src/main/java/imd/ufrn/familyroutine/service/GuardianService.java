package imd.ufrn.familyroutine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import imd.ufrn.familyroutine.model.Guardian;
import imd.ufrn.familyroutine.model.api.LoginRequest;
import imd.ufrn.familyroutine.repository.GuardianRepository;
import imd.ufrn.familyroutine.service.exception.EntityNotFoundException;

@Service
public class GuardianService {
    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ServiceMediator serviceMediator;

    public List<Guardian> findAll() {
        return this.guardianRepository.findAll();
    }

    public Guardian findGuardianById(Long guardianId) {
        return this.guardianRepository
            .findById(guardianId)
            .orElseThrow(() -> new EntityNotFoundException(guardianId, Guardian.class));
    }

    public void deleteGuardianById(Long guardianId) {
        this.serviceMediator.deleteGuardianById(guardianId);
    }

    public void deleteAllGuardians() {
        this.serviceMediator.deleteAllGuardians();
    }

    public Guardian createGuardian(Guardian newGuardian) {
        newGuardian.setPassword(passwordEncoder.encode(newGuardian.getPassword()));
        return this.guardianRepository.save(newGuardian);
    }

    public Guardian createGuardianInCascade(Guardian newGuardian) {
        return this.serviceMediator.createGuardian(newGuardian);
    }

    public Guardian authenticateGuardian(LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        
        return this.guardianRepository.findByEmail(loginRequest.getUsername()).get();
    }
}
