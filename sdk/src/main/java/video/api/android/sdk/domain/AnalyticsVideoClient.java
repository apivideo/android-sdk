package video.api.android.sdk.domain;

import video.api.android.sdk.domain.analytic.AnalyticVideo;
import video.api.android.sdk.domain.pagination.AnalyticsFilter;
import video.api.android.sdk.domain.pagination.Pager;
import video.api.android.sdk.domain.pagination.PagerBuilder;

public interface AnalyticsVideoClient {

    void getVideoAnalytics(String videoId, String period, ResponseListener<AnalyticVideo> analyticsVideoResponseListener, ResponseErrorListener responseErrorListener);

    Pager<AnalyticVideo> search(String period, PagerBuilder pagerBuilder, AnalyticsFilter analyticsFilter);

    Pager<AnalyticVideo> search(String period, AnalyticsFilter analyticsFilter);

    Pager<AnalyticVideo> list(String period, PagerBuilder pagerBuilder);

    Pager<AnalyticVideo> list(String period);

}
