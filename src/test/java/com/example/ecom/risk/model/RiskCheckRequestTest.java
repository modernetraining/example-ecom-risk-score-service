package com.example.ecom.risk.model;

import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;

public class RiskCheckRequestTest {

    @Test
    public void testRiskCheckRequest() {
        RiskCheckRequest request = new RiskCheckRequest();
        request.setAmount(new BigDecimal("500.00"));

        assertEquals(new BigDecimal("500.00"), request.getAmount());
    }
}
