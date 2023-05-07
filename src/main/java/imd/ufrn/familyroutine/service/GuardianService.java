package imd.ufrn.familyroutine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imd.ufrn.familyroutine.model.Guardian;
import imd.ufrn.familyroutine.model.Person;
import imd.ufrn.familyroutine.model.api.request.LoginRequest;
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
    private PersonService personService;

    public List<Guardian> findAll() {
        return this.guardianRepository.findAll();
    }

    public Guardian findGuardianById(Long guardianId) {
        return this.guardianRepository
            .findById(guardianId)
            .orElseThrow(() -> new EntityNotFoundException(guardianId, Guardian.class));
    }

    @Transactional
    public Guardian createGuardianInCascade(Guardian newGuardian) {
        Person personCreated = this.personService.createPerson(newGuardian);
        newGuardian.setId(personCreated.getId());
        this.createGuardian(newGuardian);
        return newGuardian;
    }

    @Transactional
    public void deleteAllGuardians() {
        List<Guardian> guardians = this.findAll();
        this.personService.deleteAllGuardians(guardians);
    }

    public void deleteGuardianById(Long guardianId) {
        this.personService.deletePersonById(guardianId);
    }

    public Guardian authenticateGuardian(LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        
        return this.guardianRepository.findByEmail(loginRequest.getUsername()).get();
    }

    private Guardian createGuardian(Guardian newGuardian) {
        newGuardian.setPassword(passwordEncoder.encode(newGuardian.getPassword()));
        return this.guardianRepository.save(newGuardian);
    }
}
