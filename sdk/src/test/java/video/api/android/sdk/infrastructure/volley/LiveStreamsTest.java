package video.api.android.sdk.infrastructure.volley;

import com.android.volley.Request;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import video.api.android.sdk.domain.LiveStream;
import video.api.android.sdk.infrastructure.LiveStreamJsonSerializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LiveStreamsTest {
    private       LiveStreams         liveStreams;
    private final TestRequestExecutor requestExecutor = new TestRequestExecutor();

    @Before
    public void setUp() {
        liveStreams = new LiveStreams(requestExecutor, new LiveStreamJsonSerializer(), null);
    }

    @Test
    public void create() throws JSONException {
        LiveStream liveStream = new LiveStream();
        liveStreams.create(liveStream, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/live-streams"));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.POST);
    }

    @Test
    public void update() throws JSONException {
        LiveStream liveStream = new LiveStream();
        liveStream.setLiveStreamId("liveSuccess");
        liveStreams.update(liveStream, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/live-streams/" + liveStream.getLiveStreamId()));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.PATCH);
    }


    @Test
    public void uploadThumbnail() {
        String liveStreamId = "liveSuccess";
        liveStreams.uploadThumbnail(liveStreamId, "thumbnailPath", null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/live-streams/" + liveStreamId + "/thumbnail"));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.POST);
    }


    @Test
    public void loadPage() {
        int page = 1;
        int pageSize = 50;
        liveStreams.loadPage(page, pageSize, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/live-streams?currentPage=" + page + "&pageSize=" + pageSize));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.GET);
    }

    @Test
    public void get() {
        String liveStreamId = "liveSuccess";
        liveStreams.get(liveStreamId, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/live-streams/" + liveStreamId));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.GET);
    }

    @Test
    public void delete() {
        String liveStreamId = "liveSuccess";
        liveStreams.delete(liveStreamId, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/live-streams/" + liveStreamId));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.DELETE);
    }

    @Test
    public void deleteThumbnail() {
        String liveStreamId = "liveSuccess";
        liveStreams.deleteThumbnail(liveStreamId, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/live-streams/" + liveStreamId + "/thumbnail"));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.DELETE);
    }
}