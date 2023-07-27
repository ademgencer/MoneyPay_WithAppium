package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Driver {

    private static AppiumDriverLocalService service;
    private static AppiumDriver<?> driver;

    private static void startAppium() {
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingAnyFreePort()
                .build();
        service.clearOutPutStreams();
        service.start();
    }

    public static AppiumDriver<?> getDriver(Device device, Application app) {
        if (driver == null) {
            startAppium();
            String apkPath = "src/test/resources/";// Apk nın olduğu adres
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("appium:udid", device.getUdid());// udid: unique device id
            capabilities.setCapability("appium:version", device.getVersion());
            capabilities.setCapability("appium:deviceName", device.getDeviceName());
            capabilities.setCapability("appium:platformName", device.getPlatformName());// zorunlu alan
            if (app.getApk().length() > 0) capabilities.setCapability("appium:app", apkPath + app.getApk());
            // appium:app -> Test edilecek App in baslangicta kurulması gereken apk dosyası varsa adresini burada veriyoruz.

            capabilities.setCapability("appium:appPackage", app.getAppPackage());// App in başlatıldığı dosyanın package ıdır
            capabilities.setCapability("appium:appActivity", app.getAppActivity());// App in başlatıldığğı appPackage taki startUp dosyasıdır
            // Main metodunun bulunduğu main class ve içinde bulunduğu package gibi düşünebiliriz

            driver = new AndroidDriver<>(service.getUrl(), capabilities);
        }
        return driver;
    }

    public static void stopService() {
        driver.quit();
        service.stop();
    }

}
