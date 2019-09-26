package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import video.api.android.sdk.domain.Token;

import static org.junit.Assert.assertEquals;

public class TokenJsonSerializerTest {

    private JsonSerializer<Token> tokenJsonSerializer;

    @Before
    public void setUp() {
        tokenJsonSerializer = new TokenJsonSerializer();
    }

    @Test(expected = JSONException.class)
    public void deserializeFailure() throws JSONException {
        tokenJsonSerializer.deserialize(new JSONObject());
    }

    @Test(expected = RuntimeException.class)
    public void deserializeAllFailure() throws JSONException {
        tokenJsonSerializer.deserialize(new JSONArray());
    }

    @Test(expected = RuntimeException.class)
    public void serializeFailure() throws JSONException {
        tokenJsonSerializer.serialize(new Token());
    }

    @Test
    public void deserializeSuccess() throws JSONException {
        Map<Object, Object> jsonParams = new HashMap<>();
        jsonParams.put("token", "token");
        Token token = tokenJsonSerializer.deserialize(new JSONObject(jsonParams));
        assertEquals("token", token.getToken());
    }

}