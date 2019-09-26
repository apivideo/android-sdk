package video.api.android.sdk.domain;

public class Quota {

    private int quotaUsed;
    private int quotaRemaining;
    private int quotaTotal;

    public Quota(int quotaUsed, int quotaRemaining, int quotaTotal) {
        this.quotaUsed = quotaUsed;
        this.quotaRemaining = quotaRemaining;
        this.quotaTotal = quotaTotal;
    }

    public Quota() {
    }

    public int getQuotaUsed() {
        return quotaUsed;
    }

    public void setQuotaUsed(int quotaUsed) {
        this.quotaUsed = quotaUsed;
    }

    public int getQuotaRemaining() {
        return quotaRemaining;
    }

    public void setQuotaRemaining(int quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }

    public int getQuotaTotal() {
        return quotaTotal;
    }

    public void setQuotaTotal(int quotaTotal) {
        this.quotaTotal = quotaTotal;
    }
}
