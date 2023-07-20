package imd.ufrn.instancepetroutine.controller;

import imd.ufrn.framework.controller.RecommendationController;
import imd.ufrn.instancepetroutine.service.RecommendationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
