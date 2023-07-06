package imd.ufrn.framework.controller;

import java.util.List;

import imd.ufrn.framework.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import imd.ufrn.framework.model.api.request.RelationRequest;
import imd.ufrn.framework.model.api.response.RelationResponse;

@CrossOrigin
@RestController
@RequestMapping("/relation")
public class RelationController {
  @Autowired
  private RelationService relationService;

  @GetMapping
  public List<RelationResponse> findAllRelations() {
    return this.relationService.findAllRelations();
  }

  @GetMapping("/by-id/{relationId}")
  public RelationResponse findRelationById(@PathVariable Long relationId) {
    return this.relationService.findRelationById(relationId);
  }

  @GetMapping("/by-dependent-id/{dependentId}")
  public List<RelationResponse> findRelationsByDependentId(@PathVariable Long dependentId) {
    return this.relationService.findRelationsByDependentId(dependentId);
  }

  @GetMapping("/by-user-id/{userId}")
  public List<RelationResponse> findRelationsByUserId(@PathVariable Long userId) {
    return this.relationService.findRelationsByUserId(userId);
  }

  @PostMapping
  public RelationResponse createRelation(@RequestBody RelationRequest newRelation) {
    return this.relationService.createRelation(newRelation);
  }

  @DeleteMapping
  public void deleteAllRelations() {
    this.relationService.deleteAllRelations();
  }

  @DeleteMapping("{relationId}")
  public void deleteRelationById(@PathVariable Long relationId) {
    this.relationService.deleteRelationById(relationId);
  }
}
