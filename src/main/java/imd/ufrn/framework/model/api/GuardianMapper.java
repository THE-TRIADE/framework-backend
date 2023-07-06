package imd.ufrn.framework.model.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import imd.ufrn.framework.model.api.response.RelationResponse;
import org.springframework.stereotype.Component;

import imd.ufrn.framework.model.Guardian;
import imd.ufrn.framework.model.api.response.FamilyGroupResponse;
import imd.ufrn.framework.model.api.response.RelationResponse;
import imd.ufrn.framework.model.api.response.GuardToGuardianResponse;
import imd.ufrn.framework.model.api.response.GuardianResponse;

@Component
public class GuardianMapper {
    public GuardianResponse mapGuardianToGuardianReponse(Guardian guardian, List<RelationResponse> relations, Set<FamilyGroupResponse> familyGroups) {
        GuardianResponse guardianResponse = new GuardianResponse();
        guardianResponse.setId(guardian.getId());
        guardianResponse.setName(guardian.getName());
        guardianResponse.setBirthDate(guardian.getBirthDate().toLocalDate());
        guardianResponse.setCpf(guardian.getCpf());
        guardianResponse.setEmail(guardian.getEmail());
        guardianResponse.setFamilyGroups(familyGroups.stream().collect(Collectors.toList()));
        List<GuardToGuardianResponse> guardToGuardianResponse = new ArrayList<>();
        relations.forEach(relation -> {
            GuardToGuardianResponse gtgr = new GuardToGuardianResponse();
            gtgr.setId(relation.getId());
            gtgr.setDaysOfWeek(relation.getDaysOfWeek());
            gtgr.setGuardianRole(relation.getUserRole());
            gtgr.setDependentId(relation.getDependentId());
            gtgr.setDependentName(relation.getDependentName());
            guardToGuardianResponse.add(gtgr);
        });
        guardianResponse.setGuards(guardToGuardianResponse);
        return guardianResponse;
    }
}
