package video.api.android.sdk.infrastructure.volley;

import com.android.volley.Request;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import video.api.android.sdk.domain.Video;
import video.api.android.sdk.domain.pagination.PagerBuilder;
import video.api.android.sdk.domain.pagination.VideoFilter;
import video.api.android.sdk.infrastructure.VideoJsonSerializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VideosTest {
    private       Videos              videos;
    private final TestRequestExecutor requestExecutor = new TestRequestExecutor();

    @Before
    public void setUp() {
        videos = new Videos(requestExecutor, new VideoJsonSerializer(), null, null);
    }

    @Test
    public void create() throws JSONException {
        Video video = new Video();
        video.setTitle("viSuccess");
        videos.create(video, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/videos"));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.POST);
    }

    @Test
    public void update() throws JSONException {
        Video video = new Video();
        video.setVideoId("viSuccess");
        video.setTitle("viSuccess");
        videos.update(video, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/videos/" + video.getVideoId()));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.PATCH);
    }

    @Test
    public void uploadThumbnail() {
        String videoId = "viSuccess";
        videos.uploadThumbnail(videoId, "thumbnailPath", null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/videos/" + videoId + "/thumbnail"));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.POST);
    }

    @Test
    public void updateThumbnailWithTimeCode() {
        String videoId = "viSuccess";
        Video video = new Video();
        video.setTitle("viSuccess");
        videos.updateThumbnailWithTimeCode(videoId, "timeCode", null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/videos/" + videoId + "/thumbnail"));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.PATCH);
    }

    @Test
    public void get() {
        String videoId = "viSuccess";
        videos.get(videoId, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/videos/" + videoId));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.GET);
    }

    @Test
    public void getStatus() {
        String videoId = "viSuccess";
        videos.getStatus(videoId, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/videos/" + videoId + "/status"));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.GET);
    }

    @Test
    public void delete() {
        String videoId = "viSuccess";
        videos.delete(videoId, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/videos/" + videoId));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.DELETE);
    }


    @Test
    public void loadPage() {
        int page = 1;
        PagerBuilder pagerBuilder = new PagerBuilder();
        pagerBuilder.withPageSize(50);
        pagerBuilder.withSortBy("publishedAt");
        pagerBuilder.withSortOrder("desc");
        videos.loadPage(page, pagerBuilder, new VideoFilter(), null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/videos?currentPage=" + page + "&pageSize=50&sortBy=publishedAt&sortOrder=desc"));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.GET);
    }


    @Test
    public void delegatedUpload() {
        String token = "token";
        videos.delegatedUpload(token, "videoPath", null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/uploadVideo?token=" + token));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.POST);
    }
}