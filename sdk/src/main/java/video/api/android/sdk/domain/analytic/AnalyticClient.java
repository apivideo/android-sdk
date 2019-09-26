package video.api.android.sdk.domain.analytic;

public class AnalyticClient {

    private String type;
    private String name;
    private String version;

    public AnalyticClient(String type, String name, String version) {
        this.type = type;
        this.name = name;
        this.version = version;
    }

    public AnalyticClient() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
