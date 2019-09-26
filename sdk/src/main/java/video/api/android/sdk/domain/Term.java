package video.api.android.sdk.domain;

public class Term {
    private String startAt;
    private String endAt;

    public Term(String startAt, String endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public Term() {
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }
}
