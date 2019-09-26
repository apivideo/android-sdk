package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import video.api.android.sdk.domain.analytic.AnalyticEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnalyticEventJsonSerializerTest {

    private JsonSerializer<AnalyticEvent> analyticEventJsonSerializer;

    @Before
    public void setUp() {
        analyticEventJsonSerializer = new AnalyticEventJsonSerializer();
    }

    @Test(expected = RuntimeException.class)
    public void deserializeFailure() throws JSONException {
        analyticEventJsonSerializer.deserialize(new JSONObject());
    }

    @Test
    public void deserializeAllEmpty() throws JSONException {
        assertTrue(analyticEventJsonSerializer.deserialize(new JSONArray()).isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void serializeFailure() throws JSONException {
        analyticEventJsonSerializer.serialize(new AnalyticEvent());
    }

    @Test
    public void deserializeAllSuccess() throws JSONException {
        Map<Object, Object> jsonEvent1 = new HashMap<>();
        jsonEvent1.put("type", "type1");
        jsonEvent1.put("emittedAt", "emittedAt1");
        jsonEvent1.put("at", 0);
        Map<Object, Object> jsonEvent2 = new HashMap<>();
        jsonEvent2.put("type", "type2");
        jsonEvent2.put("emittedAt", "emittedAt2");
        jsonEvent2.put("at", 0);
        Map<Object, Object> jsonEvent3 = new HashMap<>();
        jsonEvent3.put("type", "type3");
        jsonEvent3.put("emittedAt", "emittedAt3");
        jsonEvent3.put("at", 0);

        List<AnalyticEvent> analyticEvents = analyticEventJsonSerializer.deserialize(new JSONArray() {{
            put(new JSONObject(jsonEvent1));
            put(new JSONObject(jsonEvent2));
            put(new JSONObject(jsonEvent3));
        }});
        assertEquals("type1", analyticEvents.get(0).getType());
        assertEquals("type2", analyticEvents.get(1).getType());
        assertEquals("type3", analyticEvents.get(2).getType());

    }

}