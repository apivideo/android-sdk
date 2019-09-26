package video.api.android.sdk.domain.analytic;

import java.util.ArrayList;
import java.util.Map;

public class AnalyticVideo {
    private String                  videoId;
    private String                  videoTitle;
    private Map                     metadata;
    private ArrayList<String>       tags;
    private String                  period;
    private ArrayList<AnalyticData> data;


    public AnalyticVideo(String videoId, String videoTitle, ArrayList<String> tags, Map metadata, String period, ArrayList<AnalyticData> data) {
        this.videoId = videoId;
        this.videoTitle = videoTitle;
        this.tags = tags;
        this.metadata = metadata;
        this.period = period;
        this.data = data;
    }

    public AnalyticVideo() {
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public Map getMetadata() {
        return metadata;
    }

    public void setMetadata(Map metadata) {
        this.metadata = metadata;
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
