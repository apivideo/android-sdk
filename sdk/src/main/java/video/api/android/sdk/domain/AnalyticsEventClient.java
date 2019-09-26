package video.api.android.sdk.domain;


import video.api.android.sdk.domain.analytic.AnalyticEvent;
import video.api.android.sdk.domain.pagination.Pager;

public interface AnalyticsEventClient {

    Pager<AnalyticEvent> list(String sessionId, int pageSize);

    Pager<AnalyticEvent> list(String sessionId);
}
