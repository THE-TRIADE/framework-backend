package imd.ufrn.familyroutine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.familyroutine.model.Guardian;
import imd.ufrn.familyroutine.service.GuardianService;

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
    public Guardian findGuardianById(@PathVariable Long guardianId) {
        return this.guardianService.findGuardianById(guardianId);
    }
}
