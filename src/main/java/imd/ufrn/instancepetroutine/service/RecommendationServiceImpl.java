package imd.ufrn.instancepetroutine.service;

import imd.ufrn.framework.model.api.response.ActivityResponse;
import imd.ufrn.framework.service.RecommendationService;
import imd.ufrn.instancepetroutine.model.Category;
import imd.ufrn.instancepetroutine.model.DependentStandard;
import imd.ufrn.instancepetroutine.model.api.response.ActivityWithCategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private ActivityWithCategoryService activityWithCategoryService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DependentServiceImpl dependentService;

    @Override
    public List<String> generateRecommendation(Long dependentId) {
        List<String> recommendations = new ArrayList<>();

        List<ActivityWithCategoryResponse> activities = activityWithCategoryService.findByDependentId(dependentId);

        Map<Long, Map<String, List<ActivityWithCategoryResponse>>> activitiesByCategoryAndState = activities.stream()
                .collect(Collectors.groupingBy(ActivityWithCategoryResponse::getCategoryId,
                        Collectors.groupingBy(this::getActivityState)));

        for (Map.Entry<Long, Map<String, List<ActivityWithCategoryResponse>>> categoryEntry : activitiesByCategoryAndState.entrySet()) {
            Long categoryId = categoryEntry.getKey();
            String categoryName = getCategoryName(categoryId);

            for (Map.Entry<String, List<ActivityWithCategoryResponse>> stateEntry : categoryEntry.getValue().entrySet()) {
                String state = stateEntry.getKey();
                String translatedState = translateActivityState(state);
                List<ActivityWithCategoryResponse> stateActivities = stateEntry.getValue();
                String dependentName = getDependentName(dependentId);

                String message = "O pet " + dependentName + " possui " + stateActivities.size() + " atividade(s) na categoria " + categoryName +
                        " e no estado " + translatedState;
                recommendations.add(message);
            }
        }

        return recommendations;
    }

    private String getCategoryName(Long categoryId) {
        Category category = categoryService.findCategoryById(categoryId);
        return category.getName();
    }
    private String getDependentName(Long dependentId) {
        DependentStandard dependent = dependentService.findDependentById(dependentId);
        return dependent.getName();
    }
    private String getActivityState(ActivityResponse activity) {
        return activity.getState().toString();
    }
    private String translateActivityState(String state) {
        switch (state) {
            case "CREATED":
                return "Criada";
            case "IN_PROGRESS":
                return "Em andamento";
            case "LATE":
                return "Atrasada";
            case "DONE":
                return "Concluída";
            case "NOT_DONE":
                return "Não concluída";
            default:
                return "Desconhecido";
        }
    }

}

