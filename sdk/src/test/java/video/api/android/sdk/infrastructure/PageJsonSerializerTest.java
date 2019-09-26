package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import video.api.android.sdk.domain.Video;
import video.api.android.sdk.domain.pagination.Page;

import static org.junit.Assert.assertEquals;

public class PageJsonSerializerTest {

    private       JsonSerializer<Page<Video>> pageJsonSerializer;
    private final JsonSerializer<Video>       videoJsonSerializer = new VideoJsonSerializer();

    @Before
    public void setUp() {
        pageJsonSerializer = new PageJsonSerializer<>(videoJsonSerializer);
    }

    @Test(expected = JSONException.class)
    public void deserializeFailure() throws JSONException {
        pageJsonSerializer.deserialize(new JSONObject());
    }

    @Test(expected = RuntimeException.class)
    public void deserializeAllFailure() throws JSONException {
        pageJsonSerializer.deserialize(new JSONArray());
    }

    @Test(expected = RuntimeException.class)
    public void serializeFailure() throws JSONException {
        pageJsonSerializer.serialize(new Page<>(0,0,0,new ArrayList<>()));
    }

    @Test
    public void deserializeSuccess() throws JSONException {
        Map<Object, Object> jsonParams = new HashMap<>();
        Map<Object, Object> paginationParams = new HashMap<>();
        paginationParams.put("currentPage", 1);
        paginationParams.put("pagesTotal", 1);
        paginationParams.put("itemsTotal", 20);

        jsonParams.put("pagination", new JSONObject(paginationParams));
        jsonParams.put("data", new JSONArray());

        Page<Video> page = pageJsonSerializer.deserialize(new JSONObject(jsonParams));
        assertEquals(1, page.getCurrentPage());
        assertEquals(1, page.getPagesTotal());
        assertEquals(20, page.getItemsTotal());
    }

}