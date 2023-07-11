package imd.ufrn.framework.service;

import java.util.List;

import org.springframework.stereotype.Service;

import imd.ufrn.framework.model.Dependent;

@Service
public interface DependentService <T extends Dependent>{

    public List<T> findAll();

    public T findDependentById(Long dependentId);

    public T createDependent(T newDependent);

    public void deleteAllDependents();

    public void deleteDependentById(Long dependentId);
}
