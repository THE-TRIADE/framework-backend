package imd.ufrn.instancestudentroutine.controller;
import java.util.List;

import imd.ufrn.framework.controller.RecommendationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import imd.ufrn.instancestudentroutine.service.RecommendationServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/recommendation")
public class RecommendationControllerImpl implements RecommendationController {
    @Autowired
    private RecommendationServiceImpl recommendationService;

    @GetMapping("/{dependentId}")
    public List<String> getRecommendations(@PathVariable Long dependentId) {
        return recommendationService.generateRecommendation(dependentId);
    }
}
