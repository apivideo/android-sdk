package video.api.android.sdk.domain.pagination;

import video.api.android.sdk.domain.ResponseErrorListener;

public interface PageLoader<T> {
    void loadPage(int page, int pageSize, PageResponseListener<T> listener, final ResponseErrorListener responseErrorListener);
}
