package imd.ufrn.instancestudentroutine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.framework.model.api.response.ActivityResponse;
import imd.ufrn.framework.service.RecommendationService;
import imd.ufrn.instancestudentroutine.model.Course;
import imd.ufrn.instancestudentroutine.model.DependentStudent;
import imd.ufrn.instancestudentroutine.model.api.response.ActivityWithCourseResponse;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private ActivityWithCourseService activityWithCourseService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private DependentServiceImpl dependentService;

    @Override
    public List<String> generateRecommendation(Long dependentId) {
        List<String> recommendations = new ArrayList<>();

        List<ActivityWithCourseResponse> activities = activityWithCourseService.findByDependentId(dependentId);

        Map<Long, Map<String, List<ActivityWithCourseResponse>>> activitiesByCategoryAndState = activities.stream()
                .collect(Collectors.groupingBy(ActivityWithCourseResponse::getCourseId,
                        Collectors.groupingBy(this::getActivityState)));

        for (Map.Entry<Long, Map<String, List<ActivityWithCourseResponse>>> categoryEntry : activitiesByCategoryAndState.entrySet()) {
            Long categoryId = categoryEntry.getKey();
            String categoryName = getCourseName(categoryId);

            for (Map.Entry<String, List<ActivityWithCourseResponse>> stateEntry : categoryEntry.getValue().entrySet()) {
                String state = stateEntry.getKey();
                String translatedState = translateActivityState(state);
                List<ActivityWithCourseResponse> stateActivities = stateEntry.getValue();
                String dependentName = getDependentName(dependentId);

                String message = "O estudante " + dependentName + " possui " + stateActivities.size() + " atividade(s) na categoria " + categoryName +
                        " e no estado " + translatedState;
                recommendations.add(message);
            }
        }

        return recommendations;
    }

    private String getCourseName(Long courseId) {
        Course course = courseService.findCourseById(courseId);
        return course.getName();
    }
    private String getDependentName(Long dependentId) {
        DependentStudent dependent = dependentService.findDependentById(dependentId);
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

