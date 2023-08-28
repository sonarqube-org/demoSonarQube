package com.manish.api.automation;

import com.manish.api.automation.util.TestUtil;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MathSuiteExecution extends TestUtil {
    @Step
    @Test
    @Feature("Test CICD")
    @Severity(SeverityLevel.MINOR)
    public void addition() {
        System.out.println("addition executed.......");

    }

    @Step
    @Test
    @Feature("Test CICD")
    @Severity(SeverityLevel.BLOCKER)
    public void subtract() {
        System.out.println("add executed");

    }

    @Step
    @Test
    @Feature("Test CICD")
    @Severity(SeverityLevel.BLOCKER)
    public void multiply() {
        System.out.println("multiply executed");
    }
}
