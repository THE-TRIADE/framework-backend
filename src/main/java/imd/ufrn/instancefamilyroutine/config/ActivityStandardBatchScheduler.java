package imd.ufrn.instancefamilyroutine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import imd.ufrn.framework.config.ActivityBatchScheduler;
import imd.ufrn.instancefamilyroutine.model.ActivityStandard;
import imd.ufrn.instancefamilyroutine.repository.mappers.ActivityStandardMapper;

@Configuration
@EnableScheduling
public class ActivityStandardBatchScheduler extends ActivityBatchScheduler<ActivityStandard, ActivityStandardMapper> {

    @Override
    public ActivityStandardMapper createRowMapperInstance() {
       return new ActivityStandardMapper();
    }
    
}
