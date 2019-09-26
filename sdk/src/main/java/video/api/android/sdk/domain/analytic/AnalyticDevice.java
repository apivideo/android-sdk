package video.api.android.sdk.domain.analytic;

public class AnalyticDevice {

    private String type;
    private String vendor;
    private String model;

    public AnalyticDevice(String type, String vendor, String model) {
        this.type = type;
        this.vendor = vendor;
        this.model = model;
    }

    public AnalyticDevice() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
