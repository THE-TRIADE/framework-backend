package imd.ufrn.instancepetroutine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import imd.ufrn.framework.config.ActivityBatchScheduler;
import imd.ufrn.instancepetroutine.model.ActivityWithCategory;
import imd.ufrn.instancepetroutine.repository.mappers.ActivityWithCategoryMapper;

@Configuration
@EnableScheduling
public class ActivityStandardBatchScheduler extends ActivityBatchScheduler<ActivityWithCategory, ActivityWithCategoryMapper> {

    @Override
    public ActivityWithCategoryMapper createRowMapperInstance() {
       return new ActivityWithCategoryMapper();
    }
    
}
