package video.api.android.sdk.domain;

public class Assets {

    private String iframe;
    private String player;
    private String hls;
    private String thumbnail;

    public Assets() {
    }

    public Assets(String iframe, String player, String hls, String thumbnail) {
        this.iframe = iframe;
        this.player = player;
        this.hls = hls;
        this.thumbnail = thumbnail;
    }

    public String getIframe() {
        return iframe;
    }

    public void setIframe(String iframe) {
        this.iframe = iframe;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getHls() {
        return hls;
    }

    public void setHls(String hls) {
        this.hls = hls;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
