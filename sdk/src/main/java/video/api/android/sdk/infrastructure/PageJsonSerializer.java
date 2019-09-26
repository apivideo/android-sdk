package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import video.api.android.sdk.domain.pagination.Page;

public class PageJsonSerializer<T> implements JsonSerializer<Page<T>> {
    private final JsonSerializer<T> inner;

    public PageJsonSerializer(JsonSerializer<T> inner) {
        this.inner = inner;
    }

    public Page<T> deserialize(JSONObject data) throws JSONException {
        JSONObject paginationObject = data.getJSONObject("pagination");
        JSONArray items = data.getJSONArray("data");

        return new Page<>(
                paginationObject.getInt("currentPage"),
                paginationObject.getInt("pagesTotal"),
                paginationObject.getInt("itemsTotal"),
                inner.deserialize(items)
        );
    }

    public List<Page<T>> deserialize(JSONArray data) {
        throw new RuntimeException("Not supported");
    }

    public JSONObject serialize(Page object) {
        throw new RuntimeException("Not supported");
    }


}
