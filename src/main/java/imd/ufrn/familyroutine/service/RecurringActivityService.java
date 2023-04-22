package imd.ufrn.familyroutine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.familyroutine.model.RecurringActivity;
import imd.ufrn.familyroutine.repository.RecurringActivityRepository;

@Service
public class RecurringActivityService {

    @Autowired
    private RecurringActivityRepository recurringActivityRepository;   

    protected RecurringActivity createRecurringActivity(RecurringActivity recurringActivity) {
        return this.recurringActivityRepository.save(recurringActivity);
    }

    protected void deleteRecurringActivityById(Long recurringActivityId) {
        this.recurringActivityRepository.deleteById(recurringActivityId);
    }
}
