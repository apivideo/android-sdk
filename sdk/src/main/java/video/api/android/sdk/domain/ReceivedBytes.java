package video.api.android.sdk.domain;

public class ReceivedBytes {
    private int to;
    private int from;
    private int total;

    public ReceivedBytes() {
    }

    public ReceivedBytes(int to, int from, int total) {
        this.to = to;
        this.from = from;
        this.total = total;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
