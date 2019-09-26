package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public interface JsonSerializer<T> {

    T deserialize(JSONObject data) throws JSONException;

    List<T> deserialize(JSONArray data) throws JSONException;

    JSONObject serialize(T object) throws JSONException;
}
