package video.api.android.sdk.infrastructure.volley;

import com.android.volley.Request;

import org.junit.Before;
import org.junit.Test;

import video.api.android.sdk.domain.pagination.AnalyticsFilter;
import video.api.android.sdk.domain.pagination.PagerBuilder;
import video.api.android.sdk.infrastructure.AnalyticLiveJsonSerializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnalyticsLiveStreamTest {
    private       AnalyticsLive       analyticsLive;
    private final TestRequestExecutor requestExecutor = new TestRequestExecutor();

    @Before
    public void setUp() {
        analyticsLive = new AnalyticsLive(requestExecutor, new AnalyticLiveJsonSerializer(), null);
    }

    @Test
    public void getLiveAnalytics() {
        String liveStreamId = "viSuccess";
        String period = "2019-01-01";
        analyticsLive.getLiveAnalytics(liveStreamId, period, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/analytics/live-streams/" + liveStreamId + "?period=" + period));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.GET);
    }


    @Test
    public void loadPageOfProjectAnalytics() {
        int page = 1;
        PagerBuilder pagerBuilder = new PagerBuilder();
        pagerBuilder.withPageSize(50);
        pagerBuilder.withSortBy("publishedAt");
        pagerBuilder.withSortOrder("desc");
        String period = "2019-01-01";
        analyticsLive.loadPageOfProjectAnalytics(period, page, pagerBuilder, new AnalyticsFilter(), null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/analytics/live-streams/?period=" + period + "&currentPage=" + page + "&pageSize=50&sortBy=publishedAt&sortOrder=desc"));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.GET);
    }
}