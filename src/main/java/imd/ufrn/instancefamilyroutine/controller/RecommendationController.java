package imd.ufrn.instancefamilyroutine.controller;

import imd.ufrn.instancefamilyroutine.service.RecommendationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/recommendation")
public class RecommendationController {
    @Autowired
    private RecommendationServiceImpl recommendationService;

    @GetMapping("/{guardianId}")
    public List<String> getRecommendations(@PathVariable Long guardianId) {
        return recommendationService.generateRecommendation(guardianId);
    }
}