package com.example.ecom.risk.service;

import com.example.ecom.risk.model.RiskCheckRequest;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RiskScoreServiceTest {

    private final RiskScoreService riskScoreService = new RiskScoreService();

    @Test
    public void testLowAmountHighRisk() {
        RiskCheckRequest request = new RiskCheckRequest(new BigDecimal("50.00"));
        riskScoreService.assessRisk(request);
        assertEquals("HIGH", request.getRiskScore());
    }

    @Test
    public void testMediumAmountMediumRisk() {
        RiskCheckRequest request = new RiskCheckRequest(new BigDecimal("500.00"));
        riskScoreService.assessRisk(request);
        assertEquals("MEDIUM", request.getRiskScore());
    }

    @Test
    public void testHighAmountLowRisk() {
        RiskCheckRequest request = new RiskCheckRequest(new BigDecimal("1500.00"));
        riskScoreService.assessRisk(request);
        assertEquals("LOW", request.getRiskScore());
    }
}
