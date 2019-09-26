package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import video.api.android.sdk.domain.Video;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class VideoJsonSerializerTest {
    private JsonSerializer<Video> videoJsonSerializer;

    @Before
    public void setUp() {
        videoJsonSerializer = new VideoJsonSerializer();
    }

    @Test
    public void deserializeEmpty() throws JSONException {
        assertNull(videoJsonSerializer.deserialize(new JSONObject()).getVideoId());
    }

    @Test
    public void deserializeAllEmpty() throws JSONException {
        assertTrue(videoJsonSerializer.deserialize(new JSONArray()).isEmpty());
    }

    @Test
    public void serializeEmpty() throws JSONException {
        assertEquals(0, videoJsonSerializer.serialize(new Video()).length());
    }

    @Test
    public void deserializeMinimalSuccess() throws JSONException {
        Map<String, String> jsonParams = new HashMap<>();
        jsonParams.put("videoId", "id");
        Video video = videoJsonSerializer.deserialize(new JSONObject(jsonParams));
        assertEquals("id", video.getVideoId());
    }

    @Test
    public void deserializeMaximalSuccess() throws JSONException {
        JSONArray tags = new JSONArray() {{
            put("tag1");
            put("tag2");
        }};

        JSONArray metadata = new JSONArray() {
        };
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("key", "age");
        hashMap.put("value", "__age__");
        HashMap<Object, Object> hashMap2 = new HashMap<>();
        hashMap2.put("key", "actor");
        hashMap2.put("value", "__actor__");
        metadata.put(new JSONObject(hashMap));
        metadata.put(new JSONObject(hashMap2));

        Map<Object, Object> jsonParams = new HashMap<>();
        jsonParams.put("videoId", "id");
        jsonParams.put("title", "tit");
        jsonParams.put("description", "desc");
        jsonParams.put("public", true);
        jsonParams.put("panoramic", true);
        jsonParams.put("tags", tags);
        jsonParams.put("metadata", metadata);

        Video video = videoJsonSerializer.deserialize(new JSONObject(jsonParams));

        assertEquals("id", video.getVideoId());
        assertEquals("tit", video.getTitle());
        assertEquals("desc", video.getDescription());
        assertTrue(video.isAccessible());
        assertTrue(video.isPanoramic());
        assertEquals("tag1", video.getTags().get(0));
        assertEquals("tag2", video.getTags().get(1));
        assertEquals("__age__", video.getMetadata().get("age"));
        assertEquals("__actor__", video.getMetadata().get("actor"));
    }

    @Test
    public void deserializeAllSuccess() throws JSONException {
        Map<String, String> jsonVideo1 = new HashMap<>();
        jsonVideo1.put("videoId", "vid1");
        Map<String, String> jsonVideo2 = new HashMap<>();
        jsonVideo2.put("videoId", "vid2");
        Map<String, String> jsonVideo3 = new HashMap<>();
        jsonVideo3.put("videoId", "vid3");
        List<Video> videoList = videoJsonSerializer.deserialize(new JSONArray() {{
            put(new JSONObject(jsonVideo1));
            put(new JSONObject(jsonVideo2));
            put(new JSONObject(jsonVideo3));
        }});
        assertEquals("vid1", videoList.get(0).getVideoId());
        assertEquals("vid2", videoList.get(1).getVideoId());
        assertEquals("vid3", videoList.get(2).getVideoId());
    }

    @Test
    public void serialize() throws JSONException {
        Video video = new Video();
        video.setTitle("title");
        JSONObject jsonVideo = videoJsonSerializer.serialize(video);
        assertEquals("title", jsonVideo.getString("title"));
    }
}