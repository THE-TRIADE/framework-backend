package imd.ufrn.framework.config;

import java.time.LocalDateTime;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import imd.ufrn.framework.model.ActivityAbstract;
import imd.ufrn.framework.model.ActivityState;

public class ActivityItemProcessor<T extends ActivityAbstract> implements ItemProcessor<T, T> {

    @Override
    @Nullable
    public T process(@NonNull T activity) throws Exception {
        LocalDateTime currentDate = LocalDateTime.now();
        ActivityState state = ActivityState.CREATED;

        // Ensures that we're only dealing with activities whose state is not DONE or NOT_DONE
        if(activity.getState() == ActivityState.DONE || activity.getState() == ActivityState.NOT_DONE) {
            return activity;
        }

        LocalDateTime startDate = LocalDateTime.of(activity.getDateStart().toLocalDate(), activity.getHourStart().toLocalTime());
        LocalDateTime endDate = LocalDateTime.of(activity.getDateEnd().toLocalDate(), activity.getHourEnd().toLocalTime());

        if (isInProgress(startDate, endDate, currentDate)) {
            state = ActivityState.IN_PROGRESS;
        }
        else if (currentDate.isAfter(endDate)) {
            state = ActivityState.LATE;
        }
        activity.setState(state);
        return activity;
    }
    

    // If currentDate >= startDate && currentDate <= endDate
    private boolean isInProgress(LocalDateTime startDate,LocalDateTime endDate, LocalDateTime currentDate) {
        boolean inProgress = false;
        if (currentDate.isAfter(startDate) && currentDate.isBefore(endDate)){
            inProgress = true;
        }
        else if (currentDate.isEqual(startDate) || currentDate.isEqual(endDate)) {
            inProgress = true;
        }
        return inProgress;
    }

}
