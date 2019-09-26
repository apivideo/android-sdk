package video.api.android.sdk.domain.analytic;

public class AnalyticReferrer {

    private String url;
    private String medium;
    private String source;
    private String search_term;

    public AnalyticReferrer(String url, String medium, String source, String search_term) {
        this.url = url;
        this.medium = medium;
        this.source = source;
        this.search_term = search_term;
    }

    public AnalyticReferrer() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSearch_term() {
        return search_term;
    }

    public void setSearch_term(String search_term) {
        this.search_term = search_term;
    }
}
