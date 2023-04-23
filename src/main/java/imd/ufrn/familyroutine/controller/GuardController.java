package imd.ufrn.familyroutine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import imd.ufrn.familyroutine.model.Guard;
import imd.ufrn.familyroutine.service.GuardService;

@CrossOrigin
@RestController
@RequestMapping("/guard")
public class GuardController {
  @Autowired
  private GuardService guardService;

  @GetMapping
  public List<Guard> findAllGuards() {
    return this.guardService.findAllGuards();
  }

  @GetMapping("/by-id/{guardId}")
  public Guard findGuardById(@PathVariable Long guardId) {
    return this.guardService.findGuardById(guardId);
  }

  @GetMapping("/by-dependent-id/{dependentId}")
  public List<Guard> findGuardsByDependentId(@PathVariable Long dependentId) {
    return this.guardService.findGuardsByDependentId(dependentId);
  }

  @GetMapping("/by-guardian-id/{guardianId}")
  public List<Guard> findGuardsByGuardianId(@PathVariable Long guardianId) {
    return this.guardService.findGuardsByGuardianId(guardianId);
  }

  @PostMapping
  public Guard createGuard(@RequestBody Guard newGuard) {
    return this.guardService.createGuard(newGuard);
  }

  @DeleteMapping
  public void deleteAllGuards() {
    this.guardService.deleteAllGuards();
  }

  @DeleteMapping("{guardId}")
  public void deleteGuardById(@PathVariable Long guardId) {
    this.guardService.deleteGuardById(guardId);
  }
}
