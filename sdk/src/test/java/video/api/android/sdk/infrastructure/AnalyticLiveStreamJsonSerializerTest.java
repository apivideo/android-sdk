package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import video.api.android.sdk.domain.analytic.AnalyticLive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnalyticLiveStreamJsonSerializerTest {
    private JsonSerializer<AnalyticLive> analyticLiveJsonSerializer;

    @Before
    public void setUp() {
        analyticLiveJsonSerializer = new AnalyticLiveJsonSerializer();
    }

    @Test(expected = JSONException.class)
    public void deserializeFailure() throws JSONException {
        analyticLiveJsonSerializer.deserialize(new JSONObject());
    }

    @Test
    public void deserializeAllEmpty() throws JSONException {
        assertTrue(analyticLiveJsonSerializer.deserialize(new JSONArray()).isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void serializeFailure() throws JSONException {
        analyticLiveJsonSerializer.serialize(new AnalyticLive());
    }

    @Test
    public void deserializeSuccess() throws JSONException {
        Map<Object, Object> jsonParams = new HashMap<>();
        Map<Object, Object> liveParams = new HashMap<>();
        liveParams.put("liveStreamId", "liveStreamId");
        liveParams.put("name", "live");
        JSONArray data = new JSONArray() {
        };
        HashMap<Object, Object> hashMap = new HashMap<>();
        Map<Object, Object> sessionParams = new HashMap<>();
        sessionParams.put("sessionId", "sessionId");
        sessionParams.put("loadedAt", "loadedAt");
        sessionParams.put("endedAt", "endedAt");
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
        jsonParams.put("live", new JSONObject(liveParams));
        jsonParams.put("data", data);

        AnalyticLive analyticLive = analyticLiveJsonSerializer.deserialize(new JSONObject(jsonParams));
        assertEquals("liveStreamId", analyticLive.getLiveStreamId());
        assertEquals("live", analyticLive.getLiveName());
        assertEquals("2019", analyticLive.getPeriod());

        assertEquals("sessionId", analyticLive.getData().get(0).getSession().getSessionId());
        assertEquals("loadedAt", analyticLive.getData().get(0).getSession().getLoadedAt());
        assertEquals("endedAt", analyticLive.getData().get(0).getSession().getEndedAt());

        assertEquals("country", analyticLive.getData().get(0).getLocation().getCountry());
        assertEquals("city", analyticLive.getData().get(0).getLocation().getCity());

        assertEquals("url", analyticLive.getData().get(0).getReferrer().getUrl());
        assertEquals("medium", analyticLive.getData().get(0).getReferrer().getMedium());
        assertEquals("source", analyticLive.getData().get(0).getReferrer().getSource());
        assertEquals("searchTerm", analyticLive.getData().get(0).getReferrer().getSearch_term());

        assertEquals("type", analyticLive.getData().get(0).getDevice().getType());
        assertEquals("vendor", analyticLive.getData().get(0).getDevice().getVendor());
        assertEquals("model", analyticLive.getData().get(0).getDevice().getModel());

        assertEquals("name", analyticLive.getData().get(0).getOs().getName());
        assertEquals("shortname", analyticLive.getData().get(0).getOs().getShortname());
        assertEquals("version", analyticLive.getData().get(0).getOs().getVersion());

        assertEquals("type", analyticLive.getData().get(0).getClient().getType());
        assertEquals("name", analyticLive.getData().get(0).getClient().getName());
        assertEquals("version", analyticLive.getData().get(0).getClient().getVersion());


    }

    @Test
    public void deserializeAllSuccess() throws JSONException {
        Map<Object, Object> jsonParams1 = new HashMap<>();
        Map<Object, Object> liveParams = new HashMap<>();
        liveParams.put("liveStreamId", "liveStreamId1");
        liveParams.put("name", "live");
        JSONArray data = new JSONArray();

        jsonParams1.put("period", "2019");
        jsonParams1.put("live", new JSONObject(liveParams));
        jsonParams1.put("data", data);

        Map<Object, Object> jsonParams2 = new HashMap<>();
        Map<Object, Object> liveParams2 = new HashMap<>();
        liveParams2.put("liveStreamId", "liveStreamId2");
        liveParams2.put("name", "live");
        JSONArray data2 = new JSONArray();

        jsonParams2.put("period", "2019");
        jsonParams2.put("live", new JSONObject(liveParams2));
        jsonParams2.put("data", data2);


        List<AnalyticLive> liveList = analyticLiveJsonSerializer.deserialize(new JSONArray() {{
            put(new JSONObject(jsonParams1));
            put(new JSONObject(jsonParams2));
        }});
        assertEquals("liveStreamId1", liveList.get(0).getLiveStreamId());
        assertEquals("liveStreamId2", liveList.get(1).getLiveStreamId());
    }

}