package driver;

public enum Device {
    // Cihaza IP adresiyle kablosuz bağlanacaksak udid' e cihazın IP adresi yazılır.
    SAMSUNG_GALAXY_FAN(
            "ce10171a1be3db2f01",
            "12",
            "Samsung Galaxy Fan",
            "Android"
    ),

    EMULATOR(
            "emulator-5554",
            "12",
            "Samsung Emulator",
            "Android"
    );

    private final String udid;
    private final String version;
    private final String deviceName;
    private final String platformName;

    Device(String udid, String version, String deviceName, String platformName) {
        this.udid = udid;
        this.version = version;
        this.deviceName = deviceName;
        this.platformName = platformName;
    }

    public String getUdid() {
        return udid;
    }

    public String getVersion() {
        return version;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getPlatformName() {
        return platformName;
    }

}
