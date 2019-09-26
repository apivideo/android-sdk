package video.api.android.sdk.domain.pagination;

import video.api.android.sdk.domain.ResponseErrorListener;

public interface PageLoaderVideos<T> {
    void loadPage(int page, PagerBuilder pagerBuilder, VideoFilter videoFilter, PageResponseListener<T> listener, final ResponseErrorListener responseErrorListener);
}
