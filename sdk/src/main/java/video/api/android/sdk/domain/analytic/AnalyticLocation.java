package video.api.android.sdk.domain.analytic;

public class AnalyticLocation {

    private String country;
    private String city;

    public AnalyticLocation(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public AnalyticLocation() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
