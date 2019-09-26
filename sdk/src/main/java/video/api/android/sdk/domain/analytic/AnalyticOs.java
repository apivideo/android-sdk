package video.api.android.sdk.domain.analytic;

public class AnalyticOs {

    private String name;
    private String shortname;
    private String version;

    public AnalyticOs(String name, String shortname, String version) {
        this.name = name;
        this.shortname = shortname;
        this.version = version;
    }

    public AnalyticOs() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
