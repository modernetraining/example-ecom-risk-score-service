package com.example.ecom.risk.model;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RiskCheckRequestTest {

    @Test
    public void testRiskCheckRequest() {
        RiskCheckRequest request = new RiskCheckRequest();
        request.setAmount(new BigDecimal("500.00"));

        assertEquals(new BigDecimal("500.00"), request.getAmount());
    }
}
