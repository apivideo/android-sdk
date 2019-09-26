package video.api.android.sdk.domain;

public class Quality {

    private String quality;
    private String status;

    public Quality() {
    }

    public Quality(String quality, String status) {
        this.quality = quality;
        this.status = status;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
