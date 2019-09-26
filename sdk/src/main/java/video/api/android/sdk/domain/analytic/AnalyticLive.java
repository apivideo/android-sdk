package video.api.android.sdk.domain.analytic;

import java.util.ArrayList;

public class AnalyticLive {

    private String liveStreamId;
    private String liveName;
    private String period;
    private ArrayList<AnalyticData> data;

    public AnalyticLive(String liveStreamId, String liveName, String period, ArrayList<AnalyticData> data) {
        this.liveStreamId = liveStreamId;
        this.liveName = liveName;
        this.period = period;
        this.data = data;
    }

    public AnalyticLive() {
    }

    public String getLiveStreamId() {
        return liveStreamId;
    }

    public void setLiveStreamId(String liveStreamId) {
        this.liveStreamId = liveStreamId;
    }

    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public ArrayList<AnalyticData> getData() {
        return data;
    }

    public void setData(ArrayList<AnalyticData> data) {
        this.data = data;
    }
}
