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
import imd.ufrn.framework.model.UserRole;
import imd.ufrn.framework.model.api.UserMapper;
import imd.ufrn.framework.model.api.request.LoginRequest;
import imd.ufrn.framework.model.api.request.UserRequest;
import imd.ufrn.framework.model.api.response.GroupUserDependentResponse;
import imd.ufrn.framework.model.api.response.RelationResponse;
import imd.ufrn.framework.model.api.response.UserResponse;
import imd.ufrn.framework.repository.UserRepository;
import imd.ufrn.framework.service.exception.EntityNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RelationService relationService;
    @Autowired
    private GroupUserDependentService groupUserDependentService;

    @Lazy
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public List<UserResponse> findAll() {
        return this.userRepository
            .findAll()
            .stream()
            .map(User::getId)
            .map(this::findUserByUserId)
            .toList();
    }

    public User findUserById(Long userId) {
        return this.userRepository
                    .findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException(userId, User.class));
    }

    public UserResponse findUserByUserId(Long userId) {
        User user = this.findUserById(userId);
        List<RelationResponse> relations = this.relationService.findRelationsByUserId(userId);
        Set<GroupUserDependentResponse> groups = new HashSet<>();
        relations.stream()
            .forEach(relation -> {
                GroupUserDependentResponse group = this.groupUserDependentService.findByDependentId(relation.getDependentId());
                groups.add(group);
            });
        
        return userMapper.mapUserToUserReponse(user, relations, groups);
    }
    
    @Transactional
    public UserResponse createUser(UserRequest newUser) {
        User user = this.userMapper.mapUserRequestToUser(newUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = this.userRepository.save(user);

        List<RelationResponse> relations = this.relationService.findRelationsByUserId(user.getId());
        Set<GroupUserDependentResponse> groups = new HashSet<>();
        relations.stream()
            .forEach(relation -> {
                GroupUserDependentResponse group = this.groupUserDependentService.findByDependentId(relation.getDependentId());
                groups.add(group);
            });
        return userMapper.mapUserToUserReponse(user, relations, groups);
    }

    @Transactional
    public void deleteAllUsers() {
        this.userRepository.deleteAll();
    }

    public void deleteUserById(Long userId) {
        this.userRepository.deleteById(userId);
    }

    public UserResponse authenticateUser(LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        User user = this.userRepository
            .findByEmail(loginRequest.getUsername())
            .get();
        List<RelationResponse> relations = this.relationService.findRelationsByUserId(user.getId());
        Set<GroupUserDependentResponse> groups = new HashSet<>();
        relations.stream()
            .forEach(relation -> {
                GroupUserDependentResponse group = this.groupUserDependentService.findByDependentId(relation.getDependentId());
                groups.add(group);
            });
        return userMapper.mapUserToUserReponse(user, relations, groups);
    }

    public List<UserResponse> findAllByRole(String userRole) {
        UserRole role = UserRole.valueOf(userRole.toUpperCase());
        return 
        this.userRepository.findByRole(role.toString())
            .stream()
            .map(user -> {
                List<RelationResponse> relations = this.relationService.findRelationsByUserId(user.getId());
                Set<GroupUserDependentResponse> groups = new HashSet<>();
                relations.stream()
                    .forEach(relation -> {
                        GroupUserDependentResponse group = this.groupUserDependentService.findByDependentId(relation.getDependentId());
                        groups.add(group);
                    });
                return userMapper.mapUserToUserReponse(user, relations, groups);
            })
            .toList();
    }
}
