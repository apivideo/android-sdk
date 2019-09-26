package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import video.api.android.sdk.domain.Caption;

public class CaptionJsonSerializer implements JsonSerializer<Caption> {

    public Caption deserialize(JSONObject response) throws JSONException {
        Caption caption = new Caption();
        if (response.has("uri")) {
            caption.setUri(response.getString("uri"));
        }
        if (response.has("src")) {
            caption.setSrc(response.getString("src"));
        }
        if (response.has("srclang")) {
            caption.setSrclang(response.getString("srclang"));
        }
        if (response.has("default")) {
            caption.setDefaut(response.getBoolean("default"));
        }
        return caption;
    }

    public List<Caption> deserialize(JSONArray data) throws JSONException {
        ArrayList<Caption> captions = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            captions.add(deserialize(data.getJSONObject(i)));
        }
        return captions;
    }

    public JSONObject serialize(Caption caption) throws JSONException {
        JSONObject data = new JSONObject();
        if (caption.isDefaut() != null) {
            data.put("default", caption.isDefaut());
        }
        return data;
    }

}
