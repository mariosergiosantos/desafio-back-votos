package br.com.southsystem.cooperativismo.controller;

import br.com.southsystem.cooperativismo.domain.request.ScheduleRequest;
import br.com.southsystem.cooperativismo.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ScheduleController.class)
public class ScheduleControllerTest {

    private static final String URL_V1_SCHEDULE = "/v1/schedule";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void getSchedulesTest() throws Exception {
        mockMvc.perform(get(URL_V1_SCHEDULE)
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void newScheduleTest() throws Exception {
        ScheduleRequest scheduleRequest = new ScheduleRequest();
        scheduleRequest.setTitle("schedule test");

        mockMvc.perform(post(URL_V1_SCHEDULE)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(scheduleRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void newScheduleInvalidTest() throws Exception {
        ScheduleRequest scheduleRequest = new ScheduleRequest();
        scheduleRequest.setTitle("TI");

        mockMvc.perform(post(URL_V1_SCHEDULE)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(scheduleRequest)))
                .andExpect(status().isBadRequest());
    }

    //@Test
    public void deleteScheduleTest() throws Exception {
        mockMvc.perform(delete(URL_V1_SCHEDULE + "/1")
                .contentType("application/json"))
                .andExpect(status().isAccepted());
    }

}
