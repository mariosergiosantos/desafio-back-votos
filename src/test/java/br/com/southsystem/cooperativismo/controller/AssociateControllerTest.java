package br.com.southsystem.cooperativismo.controller;

import br.com.southsystem.cooperativismo.domain.request.AssociateRequest;
import br.com.southsystem.cooperativismo.service.AssociateService;
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

@WebMvcTest(controllers = AssociateController.class)
public class AssociateControllerTest {

    private static final String URL_V1_ASSOCIATE = "/v1/associate";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssociateService associateService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void getAssociatesTest() throws Exception {
        mockMvc.perform(get(URL_V1_ASSOCIATE)
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void postAssociatesTest() throws Exception {
        AssociateRequest associateRequest = new AssociateRequest();
        associateRequest.setName("Mário");
        associateRequest.setDocument("10010010017");

        mockMvc.perform(post(URL_V1_ASSOCIATE)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(associateRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void postAssociatesInvalidRequestTest() throws Exception {
        AssociateRequest associateRequest = new AssociateRequest();
        associateRequest.setName("Mário");
        associateRequest.setDocument("10010");

        mockMvc.perform(post(URL_V1_ASSOCIATE)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(associateRequest)))
                .andExpect(status().isBadRequest());
    }
}
