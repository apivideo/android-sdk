package video.api.android.sdk.domain.analytic;

public class AnalyticData {

    private AnalyticSession session;
    private AnalyticLocation location;
    private AnalyticReferrer referrer;
    private AnalyticDevice device;
    private AnalyticOs os;
    private AnalyticClient client;

    public AnalyticData(AnalyticSession session, AnalyticLocation location, AnalyticReferrer referrer, AnalyticDevice device, AnalyticOs os, AnalyticClient client) {
        this.session = session;
        this.location = location;
        this.referrer = referrer;
        this.device = device;
        this.os = os;
        this.client = client;
    }

    public AnalyticData() {
    }

    public AnalyticSession getSession() {
        return session;
    }

    public void setSession(AnalyticSession session) {
        this.session = session;
    }

    public AnalyticLocation getLocation() {
        return location;
    }

    public void setLocation(AnalyticLocation location) {
        this.location = location;
    }

    public AnalyticReferrer getReferrer() {
        return referrer;
    }

    public void setReferrer(AnalyticReferrer referrer) {
        this.referrer = referrer;
    }

    public AnalyticDevice getDevice() {
        return device;
    }

    public void setDevice(AnalyticDevice device) {
        this.device = device;
    }

    public AnalyticOs getOs() {
        return os;
    }

    public void setOs(AnalyticOs os) {
        this.os = os;
    }

    public AnalyticClient getClient() {
        return client;
    }

    public void setClient(AnalyticClient client) {
        this.client = client;
    }

}
