package stepdefs;

import driver.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;

public class Hooks {

    @After()
    public void after1(Scenario scenario) {
        if (scenario.isFailed()) {
            // Sadece senaryo fail olursa SS alır ve raporlara ekler.
            byte[] screenshot = Driver.getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }

    @After(order = 1)
    public void after() {
        Driver.stopService();
    }

}