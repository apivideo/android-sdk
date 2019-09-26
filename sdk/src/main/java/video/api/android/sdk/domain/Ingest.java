package video.api.android.sdk.domain;

import java.util.ArrayList;

public class Ingest {
    private String status;
    private int filesize;
    private ArrayList<ReceivedBytes> receivedBytes;

    public Ingest() {
    }

    public Ingest(String status, int filesize, ArrayList<ReceivedBytes> receivedBytes) {
        this.status = status;
        this.filesize = filesize;
        this.receivedBytes = receivedBytes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFilesize() {
        return filesize;
    }

    public void setFilesize(int filesize) {
        this.filesize = filesize;
    }

    public ArrayList<ReceivedBytes> getReceivedBytes() {
        return receivedBytes;
    }

    public void setReceivedBytes(ArrayList<ReceivedBytes> receivedBytes) {
        this.receivedBytes = receivedBytes;
    }
}
