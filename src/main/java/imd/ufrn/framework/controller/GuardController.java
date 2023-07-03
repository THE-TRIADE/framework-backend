package imd.ufrn.framework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import imd.ufrn.framework.model.api.request.GuardRequest;
import imd.ufrn.framework.model.api.response.GuardResponse;
import imd.ufrn.framework.service.GuardService;

@CrossOrigin
@RestController
@RequestMapping("/guard")
public class GuardController {
  @Autowired
  private GuardService guardService;

  @GetMapping
  public List<GuardResponse> findAllGuards() {
    return this.guardService.findAllGuards();
  }

  @GetMapping("/by-id/{guardId}")
  public GuardResponse findGuardById(@PathVariable Long guardId) {
    return this.guardService.findGuardById(guardId);
  }

  @GetMapping("/by-dependent-id/{dependentId}")
  public List<GuardResponse> findGuardsByDependentId(@PathVariable Long dependentId) {
    return this.guardService.findGuardsByDependentId(dependentId);
  }

  @GetMapping("/by-guardian-id/{guardianId}")
  public List<GuardResponse> findGuardsByGuardianId(@PathVariable Long guardianId) {
    return this.guardService.findGuardsByGuardianId(guardianId);
  }

  @PostMapping
  public GuardResponse createGuard(@RequestBody GuardRequest newGuard) {
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
