package video.api.android.sdk.domain;

public interface ResponseErrorListener {
    /** Callback method that an error has been occurred. */
    void onErrorResponse(Exception error);
}
