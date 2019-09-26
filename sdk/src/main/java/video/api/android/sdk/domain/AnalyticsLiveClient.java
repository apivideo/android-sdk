package video.api.android.sdk.domain;

import video.api.android.sdk.domain.analytic.AnalyticLive;
import video.api.android.sdk.domain.pagination.AnalyticsFilter;
import video.api.android.sdk.domain.pagination.Pager;
import video.api.android.sdk.domain.pagination.PagerBuilder;

public interface AnalyticsLiveClient {

    void getLiveAnalytics(String liveStreamId, String period, ResponseListener<AnalyticLive> analyticsLiveResponseListener, ResponseErrorListener responseErrorListener);

    Pager<AnalyticLive> search(String period, PagerBuilder pagerBuilder, AnalyticsFilter analyticsFilter);

    Pager<AnalyticLive> search(String period, AnalyticsFilter analyticsFilter);

    Pager<AnalyticLive> list(String period, PagerBuilder pagerBuilder);

    Pager<AnalyticLive> list(String period);


}
