package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import video.api.android.sdk.domain.LiveStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class LiveStreamJsonSerializerTest {
    private JsonSerializer<LiveStream> liveJsonSerializer;

    @Before
    public void setUp() {
        liveJsonSerializer = new LiveStreamJsonSerializer();
    }

    @Test
    public void deserializeEmpty() throws JSONException {
        assertNull(liveJsonSerializer.deserialize(new JSONObject()).getLiveStreamId());
    }

    @Test
    public void deserializeAllEmpty() throws JSONException {
        assertTrue(liveJsonSerializer.deserialize(new JSONArray()).isEmpty());
    }

    @Test
    public void deserializeMinimalSuccess() throws JSONException {
        Map<String, String> jsonParams = new HashMap<>();
        jsonParams.put("liveStreamId", "id");
        LiveStream liveStream = liveJsonSerializer.deserialize(new JSONObject(jsonParams));
        assertEquals("id", liveStream.getLiveStreamId());
    }

    @Test
    public void deserializeMaximalSuccess() throws JSONException {

        Map<Object, Object> jsonParams = new HashMap<>();
        jsonParams.put("liveStreamId", "id");
        jsonParams.put("streamKey", "streamKey");
        jsonParams.put("name", "name");
        jsonParams.put("record", true);
        jsonParams.put("broadcasting", true);

        LiveStream liveStream = liveJsonSerializer.deserialize(new JSONObject(jsonParams));

        assertEquals("id", liveStream.getLiveStreamId());
        assertEquals("streamKey", liveStream.getStreamKey());
        assertEquals("name", liveStream.getName());
        assertEquals(true, liveStream.isRecord());
        assertTrue(liveStream.isBroadcasting());
    }

    @Test
    public void deserializeAllSuccess() throws JSONException {
        Map<String, String> jsonLive1 = new HashMap<>();
        jsonLive1.put("liveStreamId", "live1");
        Map<String, String> jsonLive2 = new HashMap<>();
        jsonLive2.put("liveStreamId", "live2");
        Map<String, String> jsonLive3 = new HashMap<>();
        jsonLive3.put("liveStreamId", "live3");
        List<LiveStream> liveStreamList = liveJsonSerializer.deserialize(new JSONArray() {{
            put(new JSONObject(jsonLive1));
            put(new JSONObject(jsonLive2));
            put(new JSONObject(jsonLive3));
        }});
        assertEquals("live1", liveStreamList.get(0).getLiveStreamId());
        assertEquals("live2", liveStreamList.get(1).getLiveStreamId());
        assertEquals("live3", liveStreamList.get(2).getLiveStreamId());
    }

    @Test
    public void serialize() throws JSONException {
        LiveStream liveStream = new LiveStream();
        liveStream.setName("name");
        JSONObject jsonLive = liveJsonSerializer.serialize(liveStream);
        assertEquals("name", jsonLive.getString("name"));
    }
}