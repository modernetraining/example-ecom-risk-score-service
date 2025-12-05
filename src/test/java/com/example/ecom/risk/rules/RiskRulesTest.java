package com.example.ecom.risk.rules;

import com.example.ecom.risk.config.DroolsConfig;
import com.example.ecom.risk.model.RiskCheckRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DroolsConfig.class)
public class RiskRulesTest {

    @Autowired
    private KieContainer kieContainer;

    @Test
    public void testLowAmountHighRisk() {
        RiskCheckRequest request = new RiskCheckRequest(new BigDecimal("50.00"));
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(request);
        kieSession.fireAllRules();
        kieSession.dispose();

        assertEquals("HIGH", request.getRiskScore());
    }

    @Test
    public void testMediumAmountMediumRisk() {
        RiskCheckRequest request = new RiskCheckRequest(new BigDecimal("500.00"));
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(request);
        kieSession.fireAllRules();
        kieSession.dispose();

        assertEquals("MEDIUM", request.getRiskScore());
    }

    @Test
    public void testHighAmountLowRisk() {
        RiskCheckRequest request = new RiskCheckRequest(new BigDecimal("1500.00"));
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(request);
        kieSession.fireAllRules();
        kieSession.dispose();

        assertEquals("LOW", request.getRiskScore());
    }
}
