package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import video.api.android.sdk.domain.Caption;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CaptionJsonSerializerTest {

    private JsonSerializer<Caption> captionJsonSerializer;

    @Before
    public void setUp() {
        captionJsonSerializer = new CaptionJsonSerializer();
    }

    @Test
    public void deserializeEmpty() throws JSONException {
        assertNull(captionJsonSerializer.deserialize(new JSONObject()).getSrc());
    }

    @Test
    public void deserializeAllEmpty() throws JSONException {
        assertTrue(captionJsonSerializer.deserialize(new JSONArray()).isEmpty());
    }

    @Test
    public void serializeEmpty() throws JSONException {
        assertEquals(0, captionJsonSerializer.serialize(new Caption()).length());
    }


    @Test
    public void deserializeSuccess() throws JSONException {

        Map<Object, Object> jsonParams = new HashMap<>();
        jsonParams.put("uri", "uri");
        jsonParams.put("src", "src");
        jsonParams.put("srclang", "srclang");
        jsonParams.put("default", false);

        Caption caption = captionJsonSerializer.deserialize(new JSONObject(jsonParams));

        assertEquals("uri", caption.getUri());
        assertEquals("src", caption.getSrc());
        assertEquals("srclang", caption.getSrclang());
        assertEquals(false, caption.isDefaut());
    }

    @Test
    public void deserializeAllSuccess() throws JSONException {
        Map<String, String> jsonCaption1 = new HashMap<>();
        jsonCaption1.put("uri", "uri1");
        Map<String, String> jsonCaption2 = new HashMap<>();
        jsonCaption2.put("uri", "uri2");
        Map<String, String> jsonCaption3 = new HashMap<>();
        jsonCaption3.put("uri", "uri3");
        List<Caption> captionList = captionJsonSerializer.deserialize(new JSONArray() {{
            put(new JSONObject(jsonCaption1));
            put(new JSONObject(jsonCaption2));
            put(new JSONObject(jsonCaption3));
        }});
        assertEquals("uri1", captionList.get(0).getUri());
        assertEquals("uri2", captionList.get(1).getUri());
        assertEquals("uri3", captionList.get(2).getUri());
    }

    @Test
    public void serialize() throws JSONException {
        Caption caption = new Caption();
        caption.setDefaut(true);
        JSONObject jsonCaption = captionJsonSerializer.serialize(caption);
        assertTrue(jsonCaption.getBoolean("default"));
    }
}