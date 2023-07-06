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

import imd.ufrn.framework.model.User;
import imd.ufrn.framework.model.api.UserMapper;
import imd.ufrn.framework.model.api.request.LoginRequest;
import imd.ufrn.framework.model.api.request.UserRequest;
import imd.ufrn.framework.model.api.response.FamilyGroupResponse;
import imd.ufrn.framework.model.api.response.GuardResponse;
import imd.ufrn.framework.model.api.response.UserResponse;
import imd.ufrn.framework.repository.UserRepository;
import imd.ufrn.framework.service.exception.EntityNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GuardService guardService;
    @Autowired
    private FamilyGroupService familyGroupService;

    @Lazy
    @Autowired
    private UserMapper UserMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findUserById(Long userId) {
        return this.userRepository
                    .findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException(userId, User.class));
    }

    public UserResponse findUserByUserId(Long userId) {
        User user = this.userRepository
                                .findById(userId)
                                .orElseThrow(() -> new EntityNotFoundException(userId, User.class));
        List<GuardResponse> guards = this.guardService.findGuardsByGuardianId(userId);
        Set<FamilyGroupResponse> familyGroups = new HashSet<>();
        guards.stream()
            .forEach(guard -> {
                FamilyGroupResponse fg = this.familyGroupService.findByDependentId(guard.getDependentId());
                familyGroups.add(fg);
            });
        
        return UserMapper.mapUserToUserReponse(user, guards, familyGroups);
    }
    
    @Transactional
    public User createUser(UserRequest newUser) {
        User user = this.UserMapper.mapUserRequestToUser(newUser);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        
        return this.userRepository.save(user);
    }

    @Transactional
    public void deleteAllUsers() {
        this.userRepository.deleteAll();
    }

    public void deleteUserById(Long userId) {
        this.userRepository.deleteById(userId);
    }

    public User authenticateUser(LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        
        return this.userRepository.findByEmail(loginRequest.getUsername()).get();
    }
}
