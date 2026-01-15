package com.example.ecom.risk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class RiskScoreServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAssessRisk() throws Exception {
        mockMvc.perform(post("/api/risk/assess")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount\":1000.00}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.amount").value(1000.00))
                .andExpect(jsonPath("$.data.riskScore").value("LOW"));
    }
}
