package imd.ufrn.familyroutine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.familyroutine.model.Guard;
import imd.ufrn.familyroutine.model.api.GuardMapper;
import imd.ufrn.familyroutine.model.api.request.GuardRequest;
import imd.ufrn.familyroutine.model.api.response.GuardResponse;
import imd.ufrn.familyroutine.repository.GuardRepository;
import imd.ufrn.familyroutine.service.exception.EntityNotFoundException;

@Service
public class GuardService {
  @Autowired
  private GuardRepository guardRepository;
  @Autowired
  private GuardMapper guardMapper;

  public List<GuardResponse> findAllGuards() {
    return this.guardRepository.findAll().stream().map(guardMapper::mapGuardToGuardResponse).toList();
  }

  public GuardResponse findGuardById(Long guardId) {
    return this.guardMapper.mapGuardToGuardResponse(this.getGuardById(guardId));
  }

  public List<GuardResponse> findGuardsByGuardianId(Long guardianId) {
    return this.guardRepository.findByGuardianId(guardianId).stream().map(guardMapper::mapGuardToGuardResponse)
        .toList();
  }

  public List<GuardResponse> findGuardsByDependentId(Long dependentId) {
    return this.guardRepository.findByDependentId(dependentId).stream().map(guardMapper::mapGuardToGuardResponse)
        .toList();
  }

  public GuardResponse createGuard(GuardRequest newGuard) {
    Guard guard = this.guardMapper.mapGuardRequestToGuard(newGuard);
    return this.guardMapper.mapGuardToGuardResponse(this.guardRepository.save(guard));
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

  public Guard getGuardById(Long guardId) {
    return this.guardRepository.findById(guardId).orElseThrow(() -> new EntityNotFoundException(guardId, Guard.class));
  }
}
