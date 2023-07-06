package imd.ufrn.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.framework.model.Relation;
import imd.ufrn.framework.model.api.RelationMapper;
import imd.ufrn.framework.model.api.request.RelationRequest;
import imd.ufrn.framework.model.api.response.RelationResponse;
import imd.ufrn.framework.repository.RelationRepository;
import imd.ufrn.framework.service.exception.EntityNotFoundException;
import imd.ufrn.framework.service.exception.InvalidImmutableArgumentsException;

@Service
public class RelationService {
  @Autowired
  private RelationRepository relationRepository;
  @Autowired
  private RelationMapper relationMapper;
  @Autowired
  private ValidationService validationService;

  public List<RelationResponse> findAllRelations() {
    return this.relationRepository.findAll().stream().map(relationMapper::mapRelationToRelationResponse).toList();
  }

  public RelationResponse findRelationById(Long relationId) {
    return this.relationMapper.mapRelationToRelationResponse(this.getRelationById(relationId));
  }

  public List<RelationResponse> findRelationsByUserId(Long userId) {
    return this.relationRepository.findByUserId(userId).stream().map(relationMapper::mapRelationToRelationResponse)
        .toList();
  }

  public List<RelationResponse> findRelationsByDependentId(Long dependentId) {
    return this.relationRepository.findByDependentId(dependentId).stream().map(relationMapper::mapRelationToRelationResponse)
        .toList();
  }

  public RelationResponse createRelation(RelationRequest newRelation) {
    Relation relation = this.relationMapper.mapRelationRequestToRelation(newRelation);
    return this.relationMapper.mapRelationToRelationResponse(this.relationRepository.save(relation));
  }

  public Relation updateRelation(Long guardId,RelationRequest guardRequest) {
    Relation relation = this.getRelationById(guardId);
    Relation relationUpdated = this.relationMapper.mapRelationRequestToRelation(guardRequest);

    if(!relationUpdated.getDependentId().equals(relation.getDependentId())
        || !relationUpdated.getUserId().equals(relation.getUserId())) {
            throw new InvalidImmutableArgumentsException("Dependent", "User");
    }
    validationService.validDaysOfWeekOrError(guardRequest.getDaysOfWeek());

    relation.setDaysOfWeek(relationUpdated.getDaysOfWeek());
    relation.setUserRole(relationUpdated.getUserRole());

    return this.relationRepository.update(relation);
  }

  public void deleteRelationById(Long relationId) {
    this.relationRepository.deleteById(relationId);
  }

  public void deleteAllRelations() {
    this.relationRepository.deleteAll();
  }

  public Relation getRelationById(Long relationId) {
    return this.relationRepository.findById(relationId).orElseThrow(() -> new EntityNotFoundException(relationId, Relation.class));
  }
}
