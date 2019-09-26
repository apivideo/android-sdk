package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import video.api.android.sdk.domain.Token;

public class TokenJsonSerializer implements JsonSerializer<Token> {

    public Token deserialize(JSONObject response) throws JSONException {
        Token token = new Token();
        token.setToken(response.getString("token"));
        return token;
    }

    public List<Token> deserialize(JSONArray data) {
        throw new RuntimeException("Not supported");
    }

    public JSONObject serialize(Token object) {
        throw new RuntimeException("Not supported");
    }

}
