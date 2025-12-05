package com.example.ecom.risk;

import com.example.ecom.risk.model.RiskCheckRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class RiskScoreServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KieContainer kieContainer;

    @MockBean
    private KieSession kieSession;

    @Test
    public void testAssessRisk() throws Exception {
        when(kieContainer.newKieSession()).thenReturn(kieSession);

        mockMvc.perform(post("/api/risk/assess")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount\":1000.00}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.amount").value(1000.00));

        verify(kieSession).insert(any(RiskCheckRequest.class));
        verify(kieSession).fireAllRules();
        verify(kieSession).dispose();
    }
}
