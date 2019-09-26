package video.api.android.sdk.domain;

import java.util.ArrayList;
import java.util.Map;

public class Video {
    private String              videoId;
    private String              title;
    private String              description;
    private Boolean             accessible;
    private String              publishedAt;
    private ArrayList<String>   tags;
    private Map<String, String> metadata;
    private SourceVideo         source;
    private Assets              assets;
    private String              playerId;
    private Boolean             panoramic;

    public Video() {
    }

    public Video(String videoId, String title, String description, Boolean accessible, String publishedAt, ArrayList<String> tags, Map<String,String> metadata, SourceVideo source, Assets assets, String playerId, Boolean panoramic) {
        this.videoId = videoId;
        this.title = title;
        this.description = description;
        this.accessible = accessible;
        this.publishedAt = publishedAt;
        this.tags = tags;
        this.metadata = metadata;
        this.source = source;
        this.assets = assets;
        this.playerId = playerId;
        this.panoramic = panoramic;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(Boolean accessibility) {
        this.accessible = accessibility;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public SourceVideo getSource() {
        return source;
    }

    public void setSource(SourceVideo source) {
        this.source = source;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Boolean isPanoramic() {
        return panoramic;
    }

    public void setPanoramic(Boolean panoramic) {
        this.panoramic = panoramic;
    }
}
