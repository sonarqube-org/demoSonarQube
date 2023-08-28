package com.manish.api.automation;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import com.manish.api.automation.merchant.SampleServiceTest;
import com.manish.api.automation.util.TestUtil;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSuiteExecution extends TestUtil {
    @Step
    @Test
    @Feature("Test CICD")
    @Severity(SeverityLevel.MINOR)
    @Order(1)
    public void testA() {
        new SampleServiceTest().testA();
    }

    @Step
    @Test
    @Feature("Test CICD")
    @Severity(SeverityLevel.BLOCKER)
    @Order(2)
    public void TestB() {
        new SampleServiceTest().testA();
    }
}
