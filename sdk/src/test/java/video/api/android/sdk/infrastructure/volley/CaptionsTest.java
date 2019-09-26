package video.api.android.sdk.infrastructure.volley;

import com.android.volley.Request;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import video.api.android.sdk.domain.Caption;
import video.api.android.sdk.domain.CaptionClient;
import video.api.android.sdk.infrastructure.CaptionJsonSerializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CaptionsTest {
    private       CaptionClient       captionClient;
    private final TestRequestExecutor requestExecutor = new TestRequestExecutor();

    @Before
    public void setUp() {
        captionClient = new Captions(requestExecutor, new CaptionJsonSerializer());
    }

    @Test
    public void uploadCaption() {
        String videoId = "viSuccess";
        String language = "fr";
        captionClient.upload(videoId, language, "captionPath", null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/videos/" + videoId + "/captions/" + language));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.POST);
    }

    @Test
    public void showCaption() {
        String videoId = "viSuccess";
        String language = "fr";
        captionClient.get(videoId, language, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/videos/" + videoId + "/captions/" + language));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.GET);
    }

    @Test
    public void listVideoCaptions() {
        String videoId = "viSuccess";
        captionClient.getAll(videoId, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/videos/" + videoId + "/captions"));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.GET);
    }

    @Test
    public void updateCaption() throws JSONException {
        String videoId = "viSuccess";
        String language = "fr";
        Caption caption = new Caption();
        captionClient.update(videoId, language, caption, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/videos/" + videoId + "/captions/" + language));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.PATCH);
    }

    @Test
    public void deleteCaption() {
        String videoId = "viSuccess";
        String language = "fr";
        captionClient.delete(videoId, language, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/videos/" + videoId + "/captions/" + language));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.DELETE);
    }
}