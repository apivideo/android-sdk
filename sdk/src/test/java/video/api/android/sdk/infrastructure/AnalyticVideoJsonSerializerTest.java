package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import video.api.android.sdk.domain.analytic.AnalyticVideo;

import static org.junit.Assert.*;

public class AnalyticVideoJsonSerializerTest {
    private JsonSerializer<AnalyticVideo> analyticVideoJsonSerializer ;

    @Before
    public void setUp() {
        analyticVideoJsonSerializer = new AnalyticVideoJsonSerializer();
    }

    @Test(expected = JSONException.class)
    public void deserializeFailure() throws JSONException {
        analyticVideoJsonSerializer.deserialize(new JSONObject());
    }

    @Test
    public void deserializeAllEmpty() throws JSONException {
        assertTrue(analyticVideoJsonSerializer.deserialize(new JSONArray()).isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void serializeFailure() throws JSONException {
        analyticVideoJsonSerializer.serialize(new AnalyticVideo());
    }

    @Test
    public void deserializeSuccess() throws JSONException {
        Map<Object, Object> jsonParams = new HashMap<>();
        Map<Object, Object> videoParams = new HashMap<>();
        videoParams.put("videoId", "videoId");
        videoParams.put("title", "video");
        JSONArray tags = new JSONArray() {{
            put("tag1");
            put("tag2");
        }};
        JSONArray metadata = new JSONArray() {};
        HashMap<Object, Object> hashMap1 = new HashMap<>();
        hashMap1.put("key", "age");
        hashMap1.put("value", "__age__");
        HashMap<Object, Object> hashMap2 = new HashMap<>();
        hashMap2.put("key", "actor");
        hashMap2.put("value", "__actor__");
        metadata.put(new JSONObject(hashMap1));
        metadata.put(new JSONObject(hashMap2));
        videoParams.put("tags", tags);
        videoParams.put("metadata", metadata);
        JSONArray data = new JSONArray() {
        };
        HashMap<Object, Object> hashMap = new HashMap<>();
        Map<Object, Object> sessionParams = new HashMap<>();
        sessionParams.put("sessionId", "sessionId");
        sessionParams.put("loadedAt", "loadedAt");
        sessionParams.put("endedAt", "endedAt");
        sessionParams.put("metadatas",metadata);
        hashMap.put("session", sessionParams);

        Map<Object, Object> locationParams = new HashMap<>();
        locationParams.put("country", "country");
        locationParams.put("city", "city");
        hashMap.put("location", locationParams);

        Map<Object, Object> referrerParams = new HashMap<>();
        referrerParams.put("url", "url");
        referrerParams.put("medium", "medium");
        referrerParams.put("source", "source");
        referrerParams.put("searchTerm", "searchTerm");
        hashMap.put("referrer", referrerParams);

        Map<Object, Object> deviceParams = new HashMap<>();
        deviceParams.put("type", "type");
        deviceParams.put("vendor", "vendor");
        deviceParams.put("model", "model");
        hashMap.put("device", deviceParams);

        Map<Object, Object> osParams = new HashMap<>();
        osParams.put("name", "name");
        osParams.put("shortname", "shortname");
        osParams.put("version", "version");
        hashMap.put("os", osParams);

        Map<Object, Object> clientParams = new HashMap<>();
        clientParams.put("type", "type");
        clientParams.put("name", "name");
        clientParams.put("version", "version");
        hashMap.put("client", clientParams);

        data.put(new JSONObject(hashMap));

        jsonParams.put("period", "2019");
        jsonParams.put("video", new JSONObject(videoParams));
        jsonParams.put("data", data);

        AnalyticVideo analyticVideo = analyticVideoJsonSerializer.deserialize(new JSONObject(jsonParams));
        assertEquals("videoId", analyticVideo.getVideoId());
        assertEquals("video", analyticVideo.getVideoTitle());
        assertEquals("tag1", analyticVideo.getTags().get(0));
        assertEquals("tag2", analyticVideo.getTags().get(1));
        assertEquals("__age__", analyticVideo.getMetadata().get("age"));
        assertEquals("__actor__", analyticVideo.getMetadata().get("actor"));

        assertEquals("2019", analyticVideo.getPeriod());

        assertEquals("sessionId", analyticVideo.getData().get(0).getSession().getSessionId());
        assertEquals("loadedAt", analyticVideo.getData().get(0).getSession().getLoadedAt());
        assertEquals("endedAt", analyticVideo.getData().get(0).getSession().getEndedAt());
        assertEquals("__age__", analyticVideo.getData().get(0).getSession().getMetadata().get("age"));
        assertEquals("__actor__", analyticVideo.getData().get(0).getSession().getMetadata().get("actor"));

        assertEquals("country", analyticVideo.getData().get(0).getLocation().getCountry());
        assertEquals("city", analyticVideo.getData().get(0).getLocation().getCity());

        assertEquals("url", analyticVideo.getData().get(0).getReferrer().getUrl());
        assertEquals("medium", analyticVideo.getData().get(0).getReferrer().getMedium());
        assertEquals("source", analyticVideo.getData().get(0).getReferrer().getSource());
        assertEquals("searchTerm", analyticVideo.getData().get(0).getReferrer().getSearch_term());

        assertEquals("type", analyticVideo.getData().get(0).getDevice().getType());
        assertEquals("vendor", analyticVideo.getData().get(0).getDevice().getVendor());
        assertEquals("model", analyticVideo.getData().get(0).getDevice().getModel());

        assertEquals("name", analyticVideo.getData().get(0).getOs().getName());
        assertEquals("shortname", analyticVideo.getData().get(0).getOs().getShortname());
        assertEquals("version", analyticVideo.getData().get(0).getOs().getVersion());

        assertEquals("name", analyticVideo.getData().get(0).getClient().getName());
        assertEquals("type", analyticVideo.getData().get(0).getClient().getType());
        assertEquals("version", analyticVideo.getData().get(0).getClient().getVersion());


    }

    @Test
    public void deserializeAllSuccess() throws JSONException {
        Map<Object, Object> jsonParams1 = new HashMap<>();
        Map<Object, Object> videoParams = new HashMap<>();
        videoParams.put("videoId", "videoId1");
        videoParams.put("title", "video1");
        videoParams.put("metadata", new JSONArray());
        videoParams.put("tags", new JSONArray());

        JSONArray data = new JSONArray();

        jsonParams1.put("period", "2019");
        jsonParams1.put("video", new JSONObject(videoParams));
        jsonParams1.put("data", data);

        Map<Object, Object> jsonParams2 = new HashMap<>();
        Map<Object, Object> videoParams2 = new HashMap<>();
        videoParams2.put("videoId", "videoId2");
        videoParams2.put("title", "video1");
        videoParams2.put("metadata", new JSONArray());
        videoParams2.put("tags", new JSONArray());
        JSONArray data2 = new JSONArray();

        jsonParams2.put("period", "2019");
        jsonParams2.put("video", new JSONObject(videoParams2));
        jsonParams2.put("data", data2);


        List<AnalyticVideo> videoList = analyticVideoJsonSerializer.deserialize(new JSONArray() {{
            put(new JSONObject(jsonParams1));
            put(new JSONObject(jsonParams2));
        }});
        assertEquals("videoId1", videoList.get(0).getVideoId());
        assertEquals("videoId2", videoList.get(1).getVideoId());
    }

}