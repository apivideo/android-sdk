package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import video.api.android.sdk.domain.Assets;
import video.api.android.sdk.domain.LiveStream;

public class LiveStreamJsonSerializer implements JsonSerializer<LiveStream> {

    public LiveStream deserialize(JSONObject response) throws JSONException {
        LiveStream liveStream = new LiveStream();
        Assets assets = new Assets();

        if (response.has("liveStreamId")) {
            liveStream.setLiveStreamId(response.getString("liveStreamId"));
        }
        if (response.has("streamKey")) {
            liveStream.setStreamKey(response.getString("streamKey"));
        }
        if (response.has("name")) {
            liveStream.setName(response.getString("name"));
        }
        if (response.has("record")) {
            liveStream.setRecord(response.getBoolean("record"));
        }
        if (response.has("broadcasting")) {
            liveStream.setBroadcasting(response.getBoolean("broadcasting"));
        }
        if (response.has("assets")) {
            JSONObject asset = response.getJSONObject("assets");
            assets.setIframe(asset.getString("iframe"));
            assets.setPlayer(asset.getString("player"));
            assets.setHls(asset.getString("hls"));
            assets.setThumbnail(asset.getString("thumbnail"));
            liveStream.setAssets(assets);
        }
        if (response.has("playerId")) {
            liveStream.setPlayerId(response.getString("playerId"));
        }
        return liveStream;
    }

    public List<LiveStream> deserialize(JSONArray data) throws JSONException {
        ArrayList<LiveStream> liveStreams = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            liveStreams.add(deserialize(data.getJSONObject(i)));
        }
        return liveStreams;
    }

    public JSONObject serialize(LiveStream liveStream) throws JSONException {
        JSONObject data = new JSONObject();
        if (liveStream.getName() != null) {
            data.put("name", liveStream.getName());
        }
        if (liveStream.isRecord() != null) {
            data.put("record", liveStream.isRecord());
        }
        if (liveStream.getPlayerId() != null) {
            data.put("playerId", liveStream.getPlayerId());
        }
        return data;
    }


}
