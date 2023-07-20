package imd.ufrn.framework.controller;

import java.util.List;

public interface RecommendationController {
    public List<String> getRecommendations(Long userId);
}
