package imd.ufrn.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.framework.model.Spent;
import imd.ufrn.framework.model.api.SpentMapper;
import imd.ufrn.framework.model.api.request.SpentRequest;
import imd.ufrn.framework.model.api.response.SpentResponse;
import imd.ufrn.framework.repository.SpentRepository;
import imd.ufrn.framework.service.exception.EntityNotFoundException;

@Service
public class SpentService {
  @Autowired
  private SpentRepository spentRepository;
  @Autowired
  private SpentMapper spentMapper;

  public List<SpentResponse> findAllSpents() {
    return this.spentRepository.findAll().stream().map(spentMapper::mapSpentToSpentResponse).toList();
  }

  public SpentResponse findSpentById(Long spentId) {
    return this.spentMapper.mapSpentToSpentResponse(this.getSpentById(spentId));
  }

  public List<SpentResponse> findSpentsByUserId(Long userId) {
    return this.spentRepository.findByUserId(userId).stream().map(spentMapper::mapSpentToSpentResponse)
        .toList();
  }

  public List<SpentResponse> findSpentsByDependentId(Long dependentId) {
    return this.spentRepository.findByDependentId(dependentId).stream().map(spentMapper::mapSpentToSpentResponse)
        .toList();
  }

  public SpentResponse createSpent(SpentRequest newSpent) {
    Spent spent = this.spentMapper.mapSpentRequestToSpent(newSpent);
    return this.spentMapper.mapSpentToSpentResponse(this.spentRepository.save(spent));
  }

  public Spent updateSpent(Spent spent) {
    return this.spentRepository.update(spent);
  }

  public void deleteSpentById(Long spentId) {
    this.spentRepository.deleteById(spentId);
  }

  public void deleteAllSpents() {
    this.spentRepository.deleteAll();
  }

  public Spent getSpentById(Long spentId) {
    return this.spentRepository.findById(spentId).orElseThrow(() -> new EntityNotFoundException(spentId, Spent.class));
  }
}
