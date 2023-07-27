package driver;

public enum Application {

    MONEYPAY(
            "com.colendi.money_pay",
            "com.colendi.money_pay.MainActivity",
            ""
    );


    private final String appPackage;
    private final String appActivity;
    private final String apk;

    Application(String appPackage, String appActivity, String apk) {
        this.appPackage = appPackage;
        this.appActivity = appActivity;
        this.apk = apk;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public String getApk() {
        return apk;
    }
}

