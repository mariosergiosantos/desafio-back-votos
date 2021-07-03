package br.com.southsystem.cooperativismo.controller;

import br.com.southsystem.cooperativismo.domain.request.SessionVoteRequest;
import br.com.southsystem.cooperativismo.service.SessionVoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SessionVoteController.class)
public class SessionVoteControllerTest {

    private static final String URL_V1_SESSION = "/v1/session";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionVoteService sessionVoteService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void getAllSessionsTest() throws Exception {
        mockMvc.perform(get(URL_V1_SESSION)
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void openSessionTest() throws Exception {
        SessionVoteRequest sessionVoteRequest = new SessionVoteRequest();
        sessionVoteRequest.setScheduleId(1L);
        sessionVoteRequest.setTimeSession(2L);

        mockMvc.perform(post(URL_V1_SESSION + "/open")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(sessionVoteRequest)))
                .andExpect(status().isOk());
    }


}
