package imd.ufrn.instancefamilyroutine.service;

import java.util.List;

public interface RecommendationService {
    List<String> generateRecommendation(Long guardianId);
}
