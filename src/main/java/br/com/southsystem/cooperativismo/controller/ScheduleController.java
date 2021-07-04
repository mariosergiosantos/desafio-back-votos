package br.com.southsystem.cooperativismo.controller;

import br.com.southsystem.cooperativismo.domain.model.Schedule;
import br.com.southsystem.cooperativismo.domain.request.ScheduleRequest;
import br.com.southsystem.cooperativismo.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Operation(summary = "List schedule")
    @GetMapping("/v1/schedule")
    public List<Schedule> getSchedules() {
        return scheduleService.findAll();
    }

    @Operation(summary = "Create new schedule")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/v1/schedule")
    public Schedule newSchedule(@RequestBody @Valid ScheduleRequest scheduleRequest) {
        return scheduleService.registerNewSchedule(scheduleRequest);
    }

    @Operation(summary = "update schedule")
    @PutMapping("/v1/schedule/{scheduleId}")
    public Schedule updateSchedule(@RequestBody @Valid ScheduleRequest scheduleRequest, @RequestParam("scheduleId") Long scheduleId) {
        return scheduleService.updateSchedule(scheduleId, scheduleRequest);
    }

    @Operation(summary = "Delete schedule")
    @DeleteMapping("/v1/schedule/{scheduleId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteSchedule(@RequestParam("scheduleId") Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }

}
