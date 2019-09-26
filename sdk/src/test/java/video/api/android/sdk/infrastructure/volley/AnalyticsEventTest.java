package video.api.android.sdk.infrastructure.volley;

import com.android.volley.Request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnalyticsEventTest {

    private       AnalyticsEvent      analyticsEvent;
    private final TestRequestExecutor requestExecutor = new TestRequestExecutor();

    @Before
    public void setUp() {
        analyticsEvent = new AnalyticsEvent(requestExecutor, null);
    }


    @Test
    public void loadPage() {
        int page = 1;
        int pageSize = 50;
        analyticsEvent.loadPage( page, pageSize, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/analytics/sessions/" + null + "/events?currentPage=" + page + "&pageSize=" + pageSize));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.GET);
    }
}