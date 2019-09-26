package video.api.android.sdk.domain.analytic;

import java.util.Map;

public class AnalyticSession {

    private String sessionId;
    private String loadedAt;
    private String endedAt;
    private Map metadata;

    public AnalyticSession(String sessionId, String loadedAt, String endedAt, Map metadata) {
        this.sessionId = sessionId;
        this.loadedAt = loadedAt;
        this.endedAt = endedAt;
        this.metadata = metadata;
    }

    public AnalyticSession() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLoadedAt() {
        return loadedAt;
    }

    public void setLoadedAt(String loadedAt) {
        this.loadedAt = loadedAt;
    }

    public String getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(String endedAt) {
        this.endedAt = endedAt;
    }

    public Map getMetadata() {
        return metadata;
    }

    public void setMetadata(Map metadata) {
        this.metadata = metadata;
    }
}
