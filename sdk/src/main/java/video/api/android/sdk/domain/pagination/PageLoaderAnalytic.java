package video.api.android.sdk.domain.pagination;

import video.api.android.sdk.domain.ResponseErrorListener;

public interface PageLoaderAnalytic<T> {
    void loadPageOfProjectAnalytics(String period, int page, PagerBuilder pagerBuilder, AnalyticsFilter analyticsFilter, PageResponseListener<T> listener, final ResponseErrorListener responseErrorListener);
}
