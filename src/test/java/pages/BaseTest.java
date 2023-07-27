package pages;

import driver.Application;
import driver.Device;
import driver.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {

    protected AppiumDriver<?> driver;
    protected WebDriverWait wait;

    public BaseTest() {
        System.out.println("BaseTestConstructer");
        this.driver = Driver.getDriver(Device.SAMSUNG_GALAXY_FAN, Application.MONEYPAY);
        this.wait = new WebDriverWait(driver, 10);
    }

    public void clickWithText(String text) {
        click(xpathWithText(text));
    }

    public void sendKeys(By locator, CharSequence text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    public void sendKeys(By locator, CharSequence... text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    public By xpathContainsText(String text) {
        return By.xpath("//*[contains(@text, '" + text + "')]");
    }

    public By xpathContainsContentDesc(String text) {
        return By.xpath("//*[contains(@content-desc, '" + text + "')]");
    }

    public By xpathContainsContentDescByIndex(String text, int index) {
        return By.xpath("//*[contains(@content-desc, '" + text + "')][" + index + "]");
    }

    public By xpathWithText(String text) {
        return By.xpath("//*[@text='" + text + "']");
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }


    public By xpathWithAttribute(String text) {
        return xpathWithAttribute(text, 1);
    }

    public By xpathWithAttribute(String text, int index) {
        return By.xpath("//*[@*[contains(.,'" + text + "')]][" + index + "]");
    }

    public void sleep(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Texti aranan element ekranda gözükene kadar swipe eder
     *
     * @param text
     */
    public void swipeUntil(String text) {
        while (driver.findElements(xpathContainsText(text)).size() == 0) {
            swipeVertical(.7, .3);
        }
    }

    /**
     * swipe(.7,.3) -> Ekranın %70 inden %30 una kadar aşağıdan yukarıya dikey kaydırır
     * İlk parametre daha küçük olursa yukarıdan aşağıya kaydırır.
     * Ekranın sol üst köşesi (0,0), sağ altı (w1080,h1920) point şeklindedir.
     *
     * @param start 0.7 Kaydırma başlangıcı(Ekranın altına yakın)
     * @param end   0.4 Kaydırma bitişi(Ekranın üstüne yakın)
     */
    public void swipeVertical(double start, double end) {
        int w = driver.manage().window().getSize().width;// Cihaz ekranının boyunu alır
        int h = driver.manage().window().getSize().height;// Cihaz ekranının genişliğini alır

        start = Math.min(Math.max(0., start), 1.);// Negatif veya 1 den büyük değer girmeyi önlüyoruz
        end = Math.min(Math.max(0., end), 1.);// Negatif veya 1 den büyük değer girmeyi önlüyoruz

        int startPoint = (int) (start * h);// 0-1 arası girilen değeri ekranın yüksekliğiyle çarparak ekranın % sini alır
        int endPoint = (int) (end * h);// 0-1 arası girilen değeri ekranın genişliğiyle çarparak eranın % sini alır

        new TouchAction<>(driver)// Action Class gibidir, Mobil versiyonu.
                .press(PointOption.point(w / 2, startPoint))// Ekrana press eder
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))// 100 ms bekler
                .moveTo(PointOption.point(w / 2, endPoint))// Ekranın ilgili kısmına gider
                .release()// Ekrana dokunmayı bırakır
                .perform();// Bu planı yürütür.
    }

    /**
     * swipe(.7,.3) -> Ekranın %70 inden %30 una yatay kadar kaydırır
     * İlk parametre daha küçük olursa soldan sağa kaydırır.
     * Ekranın sol üst köşesi (0,0), sağ altı (w1080,h1920) point şeklindedir.
     *
     * @param start 0.7 Kaydırma başlangıcı(Ekranın sağına yakın)
     * @param end   0.4 Kaydırma bitişi(Ekranın soluna yakın)
     */
    public void swipeHorizontal(double start, double end) {
        int w = driver.manage().window().getSize().width;// Cihaz ekranının boyunu alır
        int h = driver.manage().window().getSize().height;// Cihaz ekranının genişliğini alır

        start = Math.min(Math.max(0., start), 1.);// Negatif veya 1 den büyük değer girmeyi önlüyoruz
        end = Math.min(Math.max(0., end), 1.);// Negatif veya 1 den büyük değer girmeyi önlüyoruz

        int startPoint = (int) (start * w);// 0-1 arası girilen değeri ekranın yüksekliğiyle çarparak ekranın % sini alır
        int endPoint = (int) (end * w);// 0-1 arası girilen değeri ekranın genişliğiyle çarparak eranın % sini alır

        new TouchAction<>(driver)// Action Class gibidir, Mobil versiyonu.
                .press(PointOption.point(startPoint, h / 2))// Ekrana press eder
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))// 100 ms bekler
                .moveTo(PointOption.point(endPoint, h / 2))// Ekranın ilgili kısmına gider
                .release()// Ekrana dokunmayı bırakır
                .perform();// Bu planı yürütür.
    }

    /**
     * Girilen text e sahip element bulunana kadar bekler.
     *
     * @param texts
     */
    public void waitForText(String... texts) {
        wait.until(driver -> {
            for (String text : texts) {
                if (driver.findElements(xpathContainsText(text)).size() > 0)
                    return true;
            }
            return false;
        });
    }

    public void pauseByActions(long millis) {
        new Actions(driver)
                .pause(millis)
                .build()
                .perform();
    }

    public void waitForVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void getScreenShot(String name) {

        String isim = "C:/Users/work/Desktop/Emek Projeleri/Appium_MoneyPAY/screenShots/" + name + "_" + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd_MM_yyyy")) + ".png";

        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);

        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File target = new File(isim);

        try {
            FileUtils.copyFile(source, target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
