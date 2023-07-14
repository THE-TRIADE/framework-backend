package imd.ufrn.framework.model.api;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import imd.ufrn.framework.model.User;
import imd.ufrn.framework.model.api.request.UserRequest;
import imd.ufrn.framework.model.api.response.GroupUserDependentResponse;
import imd.ufrn.framework.model.api.response.RelationResponse;
import imd.ufrn.framework.model.api.response.RelationToUserResponse;
import imd.ufrn.framework.model.api.response.UserResponse;

@Component
public class UserMapper {
    public User mapUserRequestToUser(UserRequest userRequest) {
        User user = new User();

        user.setBirthDate(Date.valueOf(userRequest.getBirthDate()));
        user.setCpf(userRequest.getCpf());
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setPassword(userRequest.getPassword());
        return user;
    }

    public UserResponse mapUserToUserReponse(User user, List<RelationResponse> relations, Set<GroupUserDependentResponse> groups) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setBirthDate(user.getBirthDate().toLocalDate());
        userResponse.setCpf(user.getCpf());
        userResponse.setEmail(user.getEmail());
        userResponse.setGroups(groups.stream().collect(Collectors.toList()));
        List<RelationToUserResponse> relationToUserResponse = new ArrayList<>();
        relations.forEach(relation -> {
            RelationToUserResponse gtgr = new RelationToUserResponse();
            gtgr.setId(relation.getId());
            gtgr.setDaysOfWeek(relation.getDaysOfWeek());
            gtgr.setUserRole(relation.getUserRole());
            gtgr.setDependentId(relation.getDependentId());
            gtgr.setDependentName(relation.getDependentName());
            relationToUserResponse.add(gtgr);
            userResponse.getRoles().add(relation.getUserRole());
        });
        userResponse.setRelations(relationToUserResponse);


        return userResponse;
    }
}
