package imd.ufrn.framework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.framework.model.Dependent;
import imd.ufrn.framework.service.DependentService;

@CrossOrigin
@RestController
@RequestMapping("/dependent")
public abstract class DependentController<T extends Dependent> {
    @Autowired
    private DependentService<T> dependentService;

    @GetMapping
    public List<T> getAllDependents() {
        return this.dependentService.findAll();
    }

    @GetMapping("{dependentId}")
    public T findDependentById(@PathVariable Long dependentId) {
        return this.dependentService.findDependentById(dependentId);
    }

    @PostMapping
    public T createDependent(@RequestBody T newDependent) {
        return this.dependentService.createDependent(newDependent);
    }

    @DeleteMapping
    public void deleteAllDependents() {
        this.dependentService.deleteAllDependents();
    }

    @DeleteMapping("{dependentId}")
    public void deleteDependentById(@PathVariable Long dependentId) {
        this.dependentService.deleteDependentById(dependentId);
    } 
}
