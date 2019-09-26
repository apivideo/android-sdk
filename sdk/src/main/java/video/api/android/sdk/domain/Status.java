package video.api.android.sdk.domain;

public class Status {
    private Ingest ingest;
    private EncodingVideo encoding;

    public Status() {
    }

    public Status(Ingest ingest, EncodingVideo encoding) {
        this.ingest = ingest;
        this.encoding = encoding;
    }

    public Ingest getIngest() {
        return ingest;
    }

    public void setIngest(Ingest ingest) {
        this.ingest = ingest;
    }

    public EncodingVideo getEncoding() {
        return encoding;
    }

    public void setEncoding(EncodingVideo encoding) {
        this.encoding = encoding;
    }
}
