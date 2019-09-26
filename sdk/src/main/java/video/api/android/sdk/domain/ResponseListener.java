package video.api.android.sdk.domain;

public interface ResponseListener<T> {
    /** Called when a response is received. */
    void onResponse(T t);
}
