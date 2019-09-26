package video.api.android.sdk.domain.pagination;

public interface PageResponseListener<T> {
    /**
     * Called when a response is received.
     */
    void onResponse(Page<T> page);
}
