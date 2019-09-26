package video.api.android.sdk.domain.analytic;

public class AnalyticEvent {

    private String type;
    private String emitted_at;
    private Integer at;
    private Integer from;
    private Integer to;

    public AnalyticEvent(String type, String emitted_at, Integer at, Integer from, Integer to) {
        this.type = type;
        this.emitted_at = emitted_at;
        this.at = at;
        this.from = from;
        this.to = to;
    }

    public AnalyticEvent() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmitted_at() {
        return emitted_at;
    }

    public void setEmitted_at(String emitted_at) {
        this.emitted_at = emitted_at;
    }

    public Integer getAt() {
        return at;
    }

    public void setAt(Integer at) {
        this.at = at;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }
}
