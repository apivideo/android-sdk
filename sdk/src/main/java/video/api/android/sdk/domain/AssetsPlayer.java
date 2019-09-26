package video.api.android.sdk.domain;

public class AssetsPlayer {
    private String logo;
    private String link;

    public AssetsPlayer(String logo, String link) {
        this.logo = logo;
        this.link = link;
    }

    public AssetsPlayer() {
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
