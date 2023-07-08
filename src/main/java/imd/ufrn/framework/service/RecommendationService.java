package imd.ufrn.framework.service;

import java.util.List;

public interface RecommendationService {
    List<String> generateRecommendation(Long userId);
}
