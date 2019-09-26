package video.api.android.sdk.domain;

public class MetadataEncodingVideo {

    private int    width;
    private int    height;
    private Double bitrate;
    private int    duration;
    private long   framerate;
    private long   samplerate;
    private String videoCodec;
    private String audioCodec;
    private String aspectRatio;

    public MetadataEncodingVideo() {
    }

    public MetadataEncodingVideo(int width, int height, Double bitrate, int duration, long framerate, long samplerate, String videoCodec, String audioCodec, String aspectRatio) {
        this.width = width;
        this.height = height;
        this.bitrate = bitrate;
        this.duration = duration;
        this.framerate = framerate;
        this.samplerate = samplerate;
        this.videoCodec = videoCodec;
        this.audioCodec = audioCodec;
        this.aspectRatio = aspectRatio;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Double getBitrate() {
        return bitrate;
    }

    public void setBitrate(Double bitrate) {
        this.bitrate = bitrate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getFramerate() {
        return framerate;
    }

    public void setFramerate(long framerate) {
        this.framerate = framerate;
    }

    public long getSamplerate() {
        return samplerate;
    }

    public void setSamplerate(long samplerate) {
        this.samplerate = samplerate;
    }

    public String getVideoCodec() {
        return videoCodec;
    }

    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }

    public String getAudioCodec() {
        return audioCodec;
    }

    public void setAudioCodec(String audioCodec) {
        this.audioCodec = audioCodec;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }
}
