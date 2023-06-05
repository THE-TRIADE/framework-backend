package imd.ufrn.familyroutine.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import imd.ufrn.familyroutine.model.Guard;
import imd.ufrn.familyroutine.model.GuardianRole;
import imd.ufrn.familyroutine.model.api.GuardMapper;
import imd.ufrn.familyroutine.model.api.request.GuardRequest;
import imd.ufrn.familyroutine.repository.GuardRepository;

@ExtendWith(SpringExtension.class)
public class GuardServiceTest {
    @InjectMocks
    private GuardService guardService;

    @Mock
    private GuardRepository guardRepository;

    @Mock
    private GuardMapper guardMapper;

    @Mock
    private ValidationService validationService;

    @Nested
    public class UpdateGuard {
        @Test
        public void shouldUpdateGuardAndReturnUpdatedGuard() {
            Long guardId = 1L;

            Guard guard = new Guard();
            guard.setId(guardId);
            guard.setDependentId(1L);
            guard.setGuardianId(1L);
            guard.setGuardianRole(GuardianRole.FATHER);

            GuardRequest guardRequest = new GuardRequest();
            guardRequest.setGuardianRole(GuardianRole.MOTHER);
            guardRequest.setDependentId(1L);
            guardRequest.setGuardianId(1L);

            Guard guardUpdated = new Guard();
            guardUpdated.setId(guardId);
            guardUpdated.setDependentId(guardRequest.getDependentId());
            guardUpdated.setGuardianId(guardRequest.getGuardianId());
            guardUpdated.setGuardianRole(guardRequest.getGuardianRole());

            Mockito.when(guardMapper.mapGuardRequestToGuard(guardRequest)).thenReturn(guardUpdated);

            Mockito.when(guardRepository.findById(guardId)).thenReturn(Optional.of(guard));

            Mockito.when(guardRepository.update(guard)).thenReturn(guard);

            Mockito.when(validationService.validDaysOfWeekOrError(guardRequest.getDaysOfWeek()))
                    .thenReturn(guardRequest.getDaysOfWeek());

            Guard updatedGuard = guardService.updateGuard(guardId, guardRequest);

            assertEquals(guard, updatedGuard);
        }
    }

    @Nested
    public class DeleteGuardById {
        @Test
        public void shouldDeleteGuardById() {
            Long guardId = 1L;

            guardService.deleteGuardById(guardId);

            verify(guardRepository, times(1)).deleteById(guardId);
        }
    }

    @Nested
    public class DeleteAllGuards {
        @Test
        public void shouldDeleteAllGuards() {
            guardService.deleteAllGuards();

            verify(guardRepository, times(1)).deleteAll();
        }
    }
}
