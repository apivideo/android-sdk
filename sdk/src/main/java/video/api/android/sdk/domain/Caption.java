package video.api.android.sdk.domain;

public class Caption {
    private String uri;
    private String src;
    private String srclang;
    private Boolean defaut;

    public Caption(String uri, String src, String srclang, Boolean defaut) {
        this.uri = uri;
        this.src = src;
        this.srclang = srclang;
        this.defaut = defaut;
    }

    public Caption() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrclang() {
        return srclang;
    }

    public void setSrclang(String srclang) {
        this.srclang = srclang;
    }

    public Boolean isDefaut() {
        return defaut;
    }

    public void setDefaut(Boolean defaut) {
        this.defaut = defaut;
    }

}
