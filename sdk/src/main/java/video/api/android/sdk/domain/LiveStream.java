package video.api.android.sdk.domain;

public class LiveStream {
    private String  liveStreamId;
    private String  streamKey;
    private String  name;
    private Boolean record;
    private boolean broadcasting;
    private Assets  assets;
    private String  playerId;

    public LiveStream(String liveStreamId, String streamKey, String name, Boolean record, boolean broadcasting, Assets assets, String playerId) {
        this.liveStreamId = liveStreamId;
        this.streamKey = streamKey;
        this.name = name;
        this.record = record;
        this.broadcasting = broadcasting;
        this.assets = assets;
        this.playerId = playerId;
    }

    public LiveStream() {
    }

    public String getLiveStreamId() {
        return liveStreamId;
    }

    public void setLiveStreamId(String liveStreamId) {
        this.liveStreamId = liveStreamId;
    }

    public String getStreamKey() {
        return streamKey;
    }

    public void setStreamKey(String streamKey) {
        this.streamKey = streamKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isRecord() {
        return record;
    }

    public void setRecord(Boolean record) {
        this.record = record;
    }

    public boolean isBroadcasting() {
        return broadcasting;
    }

    public void setBroadcasting(boolean broadcasting) {
        this.broadcasting = broadcasting;
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
}
