package imd.ufrn.instancestudentroutine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import imd.ufrn.framework.config.ActivityBatchScheduler;
import imd.ufrn.instancestudentroutine.model.ActivityWithCourse;
import imd.ufrn.instancestudentroutine.repository.mappers.ActivityWithCourseMapper;

@Configuration
@EnableScheduling
public class ActivityWithCourseBatchScheduler extends ActivityBatchScheduler<ActivityWithCourse, ActivityWithCourseMapper> {

    @Override
    public ActivityWithCourseMapper createRowMapperInstance() {
       return new ActivityWithCourseMapper();
    }
    
}
