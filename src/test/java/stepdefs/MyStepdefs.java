package stepdefs;

import driver.Application;
import driver.Device;
import driver.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import locators.Locator;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BaseTest;

public class MyStepdefs extends BaseTest implements Locator {
    @Given("Kullanici uygulamaya giriş yapar")
    public void kullaniciUygulamayaGirişYapar() {
        System.out.println("StepdefsClass");
        //driver = Driver.getDriver(Device.SAMSUNG_GALAXY_FAN, Application.MONEYPAY);
        //wait = new WebDriverWait(driver, 10);
        // BaseTest Constructer inda initilize ettik, burada da yapılabilirdi.
    }

    @When("{string} butonuna tiklar")
    public void butonunaTiklar(String contentDesc) {
        click(xpathContainsContentDesc(contentDesc));
    }

    @Then("Telefon numarasi input alani gormelidir")
    public void telefonNumarasiInputAlaniGormelidir() {
        waitForVisible(lGsmInput);
    }

    @When("Geçerli fakat yanlis bir telefon numarasi girer ve {string} butonuna tiklar")
    public void yanlisBirTelefonNumarasiGirer(String contentDesc) {
        sendKeys(lGsmInput, "5449991188");
        click(xpathContainsContentDescByIndex(contentDesc,2));
    }

    @Then("Geçersiz giris olduguna dair uyari yazisini görmeli")
    public void yazisiniGörmeli() {
        waitForVisible(lHataliGirisUyariYazisi);
        getScreenShot("Uyari");
        Driver.stopService();
    }
}
