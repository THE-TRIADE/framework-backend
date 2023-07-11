package imd.ufrn.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.framework.model.DependentGroup;
import imd.ufrn.framework.repository.DependentGroupRepository;

@Service
public class DependentGroupService {
    @Autowired
    private DependentGroupRepository dependentGroupRepository;

    protected List<DependentGroup> findDependentGroupsByDependentId(Long dependentId) { 
        return this.dependentGroupRepository.findByDependentId(dependentId);
    }

    protected List<DependentGroup> findDependentGroupsByGroupId(Long groupId) { 
        return this.dependentGroupRepository.findByGroupId(groupId);
    }

    protected DependentGroup createDependentGroup(DependentGroup dependentGroup) { 
        return this.dependentGroupRepository.save(dependentGroup);
    }
}
