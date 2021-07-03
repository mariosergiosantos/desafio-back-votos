package br.com.southsystem.cooperativismo.service;

import br.com.southsystem.cooperativismo.domain.model.Schedule;
import br.com.southsystem.cooperativismo.domain.request.ScheduleRequest;
import br.com.southsystem.cooperativismo.exception.NotFoundException;
import br.com.southsystem.cooperativismo.repostory.ScheduleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private ScheduleService scheduleService;

    @Test
    public void registerNewScheduleTest() {
        ScheduleRequest scheduleRequest = new ScheduleRequest();
        scheduleRequest.setTitle("Exemple title");

        Schedule schedule = new Schedule(scheduleRequest.getTitle());

        when(scheduleRepository.save(schedule)).thenReturn(schedule);

        Schedule response = scheduleService.registerNewSchedule(scheduleRequest);

        Assert.assertNull(response);

    }

    @Test
    public void registerNewScheduleErrorTest() {
        ScheduleRequest scheduleRequest = new ScheduleRequest();
        scheduleRequest.setTitle("Ex");

        Schedule schedule = new Schedule(scheduleRequest.getTitle());

        when(scheduleRepository.save(schedule)).thenThrow(new DataIntegrityViolationException(""));

        scheduleService.registerNewSchedule(scheduleRequest);

        verify(scheduleRepository, never()).save(schedule);
    }

    @Test
    public void findByIdTest() {
        Long scheduleId = 1L;
        when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(getSchedule()));

        scheduleService.findById(scheduleId);

        verify(scheduleService).findById(scheduleId);
    }

    @Test
    public void findByIdErrorTest() {
        Long scheduleId = 1L;
        when(scheduleRepository.findById(scheduleId)).thenThrow(new NotFoundException(""));

        verify(scheduleService, never()).findById(scheduleId);
    }

    public Schedule getSchedule() {
        Schedule schedule = new Schedule();
        schedule.setTitle("Teste");
        schedule.setCreatedAt(LocalDateTime.now());
        schedule.setId(1L);
        return schedule;
    }
}
