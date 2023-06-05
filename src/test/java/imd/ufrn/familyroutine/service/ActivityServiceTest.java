package imd.ufrn.familyroutine.service;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import imd.ufrn.familyroutine.model.Activity;
import imd.ufrn.familyroutine.model.api.ActivityMapper;
import imd.ufrn.familyroutine.model.api.request.ActivityRequest;
import imd.ufrn.familyroutine.model.api.response.ActivityResponse;
import imd.ufrn.familyroutine.repository.ActivityRepository;

@ExtendWith(SpringExtension.class)
public class ActivityServiceTest {

    @InjectMocks
    private ActivityService activityService;

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private ActivityMapper activityMapper;

    @Nested
    public class CreateActivity {
        /*
         * TODO Lucas Vinícius
         * Positivo simples OK
         * Negativo com entrada válida - dataInicial + horaInicial > dataFinal + horaFinal OK
         * Negativo com entrada válida - repeat == true, mas repeatUntil == null OK
         * Negativo com entrada válida - repeat == true, mas daysToRepeat[i] < 1 OK
         * 
         * TODO Leonardo David
         * Negativo com entrada válida - repeat == true, mas repeatUntil < dataInicial OK
         * Negativo com entrada válida - repeat == true, mas daysToRepeat == null OK
         * Negativo com entrada válida - repeat == true, mas daysToRepeat[i] > 7 OK
         */
        @Nested
        public class WhenTheRequestParamsAreOk {

            private LocalDateTime dateTimeStart;
            private LocalDateTime dateTimeEnd;

            @Test
            public void shouldReturnAnActivity() {
                dateTimeStart = LocalDateTime.now();
                dateTimeEnd = LocalDateTime.now().plusHours(2);

                ActivityRequest activityRequest = new ActivityRequest();
                activityRequest.setName("Activity Test");
                activityRequest.setDateStart(dateTimeStart.toLocalDate());
                activityRequest.setDateEnd(dateTimeEnd.toLocalDate());
                activityRequest.setHourStart(dateTimeStart.toLocalTime());
                activityRequest.setHourEnd(dateTimeEnd.toLocalTime());
                activityRequest.setDependentId(2L);
                activityRequest.setCurrentGuardian(1L);
                activityRequest.setActor(2L);
                activityRequest.setCreatedBy(1L);
                activityRequest.setRepeat(false);

                Activity activity = new Activity();
                activity.setId(1L);
                activity.setDateStart(Date.valueOf(activityRequest.getDateStart()));
                activity.setDateEnd(Date.valueOf(activityRequest.getDateEnd()));
                activity.setHourStart(Time.valueOf(activityRequest.getHourStart()));
                activity.setHourEnd(Time.valueOf(activityRequest.getHourStart()));
                activity.setDependentId(activityRequest.getDependentId());
                activity.setCurrentGuardian(activityRequest.getCurrentGuardian());
                activity.setActor(activityRequest.getActor());
                activity.setCreatedBy(activityRequest.getCreatedBy());

                ActivityResponse activityResponse = new ActivityResponse();
                activityResponse.setId(activity.getId());
                activityResponse.setName(activity.getName());
                activityResponse.setDateStart(activity.getDateStart());
                activityResponse.setDateEnd(activity.getDateEnd());
                activityResponse.setHourStart(activity.getHourStart());
                activityResponse.setHourEnd(activity.getHourEnd());
                activityResponse.setCurrentGuardianId(activity.getCurrentGuardian());
                activityResponse.setActorId(activity.getActor());
                activityResponse.setCreatedById(activity.getCreatedBy());

                Mockito.when(activityMapper.mapActivityRequestToActivity(activityRequest)).thenReturn(activity);
                Mockito.when(activityService.createActivity(activity)).thenReturn(activity);
                Mockito.when(activityMapper.mapActivityToActivityResponse(activity)).thenReturn(activityResponse);


                ActivityResponse response = activityService.handleActivityRequest(activityRequest);


            }


        }
    }
}
