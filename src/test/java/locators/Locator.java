package locators;

import org.openqa.selenium.By;

public interface Locator {
    By lGsmInput = By.className("android.widget.EditText");
    By lHataliGirisUyariYazisi = By.xpath("//android.view.View[@content-desc=\"Girilen GSM'e ait kullanıcı bulunmamaktadır.\"]");


}
