package br.com.southsystem.cooperativismo.service;

import br.com.southsystem.cooperativismo.domain.model.Schedule;
import br.com.southsystem.cooperativismo.domain.request.ScheduleRequest;
import br.com.southsystem.cooperativismo.exception.BusinessException;
import br.com.southsystem.cooperativismo.exception.NotFoundException;
import br.com.southsystem.cooperativismo.repostory.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Schedule registerNewSchedule(ScheduleRequest scheduleRequest) {
        Schedule schedule;
        try {
            schedule = scheduleRepository.save(new Schedule(scheduleRequest.getTitle()));
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Pauta já cadastrada. Por favor criar uma nova");
        }

        return schedule;
    }

    public Schedule findById(Long pautaId) {
        return scheduleRepository.findById(pautaId).orElseThrow(() ->
                new NotFoundException(String.format("Pauta com código %d não encontrado", pautaId)));
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public Schedule updateSchedule(Long scheduleId, ScheduleRequest scheduleRequest) {
        Schedule schedule;
        try {
            schedule = scheduleRepository.findById(scheduleId)
                    .orElseThrow(() -> new NotFoundException(String.format("Pauta com código %d não encontrado", scheduleId)));

            schedule.setTitle(scheduleRequest.getTitle());

            schedule = scheduleRepository.save(schedule);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Pauta já cadastrada. Por favor criar uma nova");
        }

        return schedule;
    }

    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }
}
