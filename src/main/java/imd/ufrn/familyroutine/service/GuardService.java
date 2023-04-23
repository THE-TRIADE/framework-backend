package imd.ufrn.familyroutine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.familyroutine.model.Guard;
import imd.ufrn.familyroutine.repository.GuardRepository;
import imd.ufrn.familyroutine.service.exception.EntityNotFoundException;

@Service
public class GuardService {
  @Autowired
  private GuardRepository guardRepository;

  public List<Guard> findAllGuards() {
    return this.guardRepository.findAll();
  }

  public Guard findGuardById(Long guardId) {
    return this.guardRepository.findById(guardId).orElseThrow(() -> new EntityNotFoundException(guardId, Guard.class));
  }

  public List<Guard> findGuardsByGuardianId(Long guardianId) {
    return this.guardRepository.findByGuardianId(guardianId);
  }

  public List<Guard> findGuardsByDependentId(Long dependentId) {
    return this.guardRepository.findByDependentId(dependentId);
  }

  public Guard createGuard(Guard newGuard) {
    return this.guardRepository.save(newGuard);
  }

  public Guard updateGuard(Guard guard) {
    return this.guardRepository.update(guard);
  }

  public void deleteGuardById(Long guardId) {
    this.guardRepository.deleteById(guardId);
  }

  public void deleteAllGuards() {
    this.guardRepository.deleteAll();
  }
}
