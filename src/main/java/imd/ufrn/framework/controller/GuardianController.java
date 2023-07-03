package imd.ufrn.framework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.framework.model.Guardian;
import imd.ufrn.framework.model.api.request.LoginRequest;
import imd.ufrn.framework.model.api.response.GuardianResponse;
import imd.ufrn.framework.service.GuardianService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/guardian")
public class GuardianController {
    @Autowired
    private GuardianService guardianService;

    @GetMapping
    public List<Guardian> getAllGuardians() {
        return this.guardianService.findAll();
    }

    @GetMapping("{guardianId}")
    public GuardianResponse findGuardianById(@PathVariable Long guardianId) {
        return this.guardianService.findGuardianByGuardianId(guardianId);
    }

    @PostMapping
    public Guardian createGuardian(@RequestBody Guardian newGuardian) {
        return this.guardianService.createGuardianInCascade(newGuardian);
    }

    @PostMapping("/login")
    public Guardian logInGuardian(@RequestBody @Valid LoginRequest loginRequest) {
        return this.guardianService.authenticateGuardian(loginRequest);
    }

    @DeleteMapping
    public void deleteAllGuardians() {
        this.guardianService.deleteAllGuardians();
    }

    @DeleteMapping("{guardianId}")
    public void deleteGuardianById(@PathVariable Long guardianId) {
        this.guardianService.deleteGuardianById(guardianId);
    } 
}
