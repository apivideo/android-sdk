package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import video.api.android.sdk.domain.analytic.AnalyticEvent;

public class AnalyticEventJsonSerializer implements JsonSerializer<AnalyticEvent> {

    public AnalyticEvent deserialize(JSONObject response) {
        throw new RuntimeException("Not supported");
    }

    @Override
    public List<AnalyticEvent> deserialize(JSONArray data) throws JSONException {
        ArrayList<AnalyticEvent> analyticEventArrayList = new ArrayList<>();

        for (int i = 0; i < data.length(); i++) {
            JSONObject jsonObject1 = data.getJSONObject(i);
            AnalyticEvent analyticEvent = new AnalyticEvent();
            analyticEvent.setType(jsonObject1.getString("type"));
            analyticEvent.setEmitted_at(jsonObject1.getString("emittedAt"));
            if (jsonObject1.has("at")) {
                analyticEvent.setAt(jsonObject1.getInt("at"));
            }
            if (jsonObject1.has("from")) {
                analyticEvent.setFrom(jsonObject1.getInt("from"));
            }
            if (jsonObject1.has("to")) {
                analyticEvent.setTo(jsonObject1.getInt("to"));
            }
            analyticEventArrayList.add(analyticEvent);
        }

        return analyticEventArrayList;
    }

    @Override
    public JSONObject serialize(AnalyticEvent object) {
        throw new RuntimeException("Not supported");
    }

}
