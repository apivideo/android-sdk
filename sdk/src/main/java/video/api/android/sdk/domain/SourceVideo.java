package video.api.android.sdk.domain;

public class SourceVideo {
    private String type;
    private String uri;
//    private String status;
//    private int filesize;
//    private ArrayList<ReceivedBytes> receivedBytes;

    public SourceVideo() {
    }

    public SourceVideo(String type, String uri/*, String status, int filesize, ArrayList<ReceivedBytes> receivedBytes*/) {
        this.type = type;
        this.uri = uri;
//        this.status = status;
//        this.filesize = filesize;
//        this.receivedBytes = receivedBytes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public int getFilesize() {
//        return filesize;
//    }
//
//    public void setFilesize(int filesize) {
//        this.filesize = filesize;
//    }
//
//    public ArrayList<ReceivedBytes> getReceivedBytes() {
//        return receivedBytes;
//    }
//
//    public void setReceivedBytes(ArrayList<ReceivedBytes> receivedBytes) {
//        this.receivedBytes = receivedBytes;
//    }
}
