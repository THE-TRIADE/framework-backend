package imd.ufrn.framework.model.api;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import imd.ufrn.framework.model.User;
import imd.ufrn.framework.model.api.request.UserRequest;
import imd.ufrn.framework.model.api.response.FamilyGroupResponse;
import imd.ufrn.framework.model.api.response.GuardResponse;
import imd.ufrn.framework.model.api.response.GuardToUserResponse;
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

    public UserResponse mapUserToUserReponse(User user, List<GuardResponse> guards, Set<FamilyGroupResponse> familyGroups) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setBirthDate(user.getBirthDate().toLocalDate());
        userResponse.setCpf(user.getCpf());
        userResponse.setEmail(user.getEmail());
        userResponse.setFamilyGroups(familyGroups.stream().collect(Collectors.toList()));
        List<GuardToUserResponse> guardToGuardianResponse = new ArrayList<>();
        guards.forEach(guard -> {
            GuardToUserResponse gtgr = new GuardToUserResponse();
            gtgr.setId(guard.getId());
            gtgr.setDaysOfWeek(guard.getDaysOfWeek());
            gtgr.setUserRole(guard.getGuardianRole());
            gtgr.setDependentId(guard.getDependentId());
            gtgr.setDependentName(guard.getDependentName());
            guardToGuardianResponse.add(gtgr);
        });
        userResponse.setGuards(guardToGuardianResponse);
        return userResponse;
    }
}
