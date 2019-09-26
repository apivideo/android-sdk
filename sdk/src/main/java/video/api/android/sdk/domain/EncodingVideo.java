package video.api.android.sdk.domain;

import java.util.ArrayList;

public class EncodingVideo {

    private boolean playable;
    private ArrayList<Quality> Qualities;
    private MetadataEncodingVideo metadata;

    public EncodingVideo() {
    }

    public EncodingVideo(boolean playable, ArrayList<Quality> qualities, MetadataEncodingVideo metadata) {
        this.playable = playable;
        Qualities = qualities;
        this.metadata = metadata;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public ArrayList<Quality> getQualities() {
        return Qualities;
    }

    public void setQualities(ArrayList<Quality> qualities) {
        Qualities = qualities;
    }

    public MetadataEncodingVideo getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataEncodingVideo metadata) {
        this.metadata = metadata;
    }
}
