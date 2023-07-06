package imd.ufrn.framework.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import imd.ufrn.framework.model.Guardian;
import imd.ufrn.framework.model.Person;
import imd.ufrn.framework.model.api.GuardianMapper;
import imd.ufrn.framework.model.api.request.LoginRequest;
import imd.ufrn.framework.model.api.response.GroupUserDependentResponse;
import imd.ufrn.framework.model.api.response.GuardResponse;
import imd.ufrn.framework.model.api.response.GuardianResponse;
import imd.ufrn.framework.repository.GuardianRepository;
import imd.ufrn.framework.service.exception.EntityNotFoundException;

@Service
public class GuardianService {
    @Autowired
    private GuardianRepository guardianRepository;
    @Autowired
    private GuardService guardService;
    @Autowired
    private GroupUserDependentService familyGroupService;

    @Lazy
    @Autowired
    private GuardianMapper guardianMapper;
    
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

    public GuardianResponse findGuardianByGuardianId(Long guardianId) {
        Guardian guardian = this.guardianRepository
                                .findById(guardianId)
                                .orElseThrow(() -> new EntityNotFoundException(guardianId, Guardian.class));
        List<GuardResponse> guards = this.guardService.findGuardsByGuardianId(guardianId);
        Set<GroupUserDependentResponse> familyGroups = new HashSet<>();
        guards.stream()
            .forEach(guard -> {
                GroupUserDependentResponse fg = this.familyGroupService.findByDependentId(guard.getDependentId());
                familyGroups.add(fg);
            });
        
        return guardianMapper.mapGuardianToGuardianReponse(guardian, guards, familyGroups);
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
