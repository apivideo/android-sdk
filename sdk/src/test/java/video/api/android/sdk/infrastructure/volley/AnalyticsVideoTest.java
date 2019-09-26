package video.api.android.sdk.infrastructure.volley;

import com.android.volley.Request;

import org.junit.Before;
import org.junit.Test;

import video.api.android.sdk.domain.pagination.AnalyticsFilter;
import video.api.android.sdk.domain.pagination.PagerBuilder;
import video.api.android.sdk.infrastructure.AnalyticVideoJsonSerializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnalyticsVideoTest {
    private       AnalyticsVideo      analyticsVideo;
    private final TestRequestExecutor requestExecutor = new TestRequestExecutor();

    @Before
    public void setUp() {
        analyticsVideo = new AnalyticsVideo(requestExecutor, new AnalyticVideoJsonSerializer(), null);
    }

    @Test
    public void getVideoAnalytics() {
        String videoId = "viSuccess";
        String period = "2019-01-01";
        analyticsVideo.getVideoAnalytics(videoId, period, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/analytics/videos/" + videoId + "?period=" + period));
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
        analyticsVideo.loadPageOfProjectAnalytics(period, page, pagerBuilder, new AnalyticsFilter(), null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/analytics/videos/?period=" + period + "&currentPage=" + page + "&pageSize=50&sortBy=publishedAt&sortOrder=desc"));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.GET);
    }
}