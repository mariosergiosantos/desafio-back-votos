package br.com.southsystem.cooperativismo.controller;

import br.com.southsystem.cooperativismo.domain.model.Schedule;
import br.com.southsystem.cooperativismo.domain.request.ScheduleRequest;
import br.com.southsystem.cooperativismo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/v1/schedule")
    public List<Schedule> getSchedules() {
        return scheduleService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/v1/schedule")
    public Schedule newSchedule(@RequestBody @Valid ScheduleRequest scheduleRequest) {
        return scheduleService.registerNewSchedule(scheduleRequest);
    }

    @PutMapping("/v1/schedule/{scheduleId}")
    public Schedule updateSchedule(@RequestBody @Valid ScheduleRequest scheduleRequest, @RequestParam("scheduleId") Long scheduleId) {
        return scheduleService.updateSchedule(scheduleId, scheduleRequest);
    }

    @DeleteMapping("/v1/schedule/{scheduleId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteSchedule(@RequestParam("scheduleId") Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }

}
