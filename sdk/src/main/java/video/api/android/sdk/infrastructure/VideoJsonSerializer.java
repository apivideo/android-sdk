package video.api.android.sdk.infrastructure;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import video.api.android.sdk.domain.Assets;
import video.api.android.sdk.domain.SourceVideo;
import video.api.android.sdk.domain.Video;

public class VideoJsonSerializer implements JsonSerializer<Video> {

    public Video deserialize(JSONObject data) throws JSONException {
        Video video = new Video();
        SourceVideo source = new SourceVideo();
        Assets assets = new Assets();
        if (data.has("videoId")) {
            video.setVideoId(data.getString("videoId"));
        }
        if (data.has("title")) {
            video.setTitle(data.getString("title"));
        }
        if (data.has("description")) {
            video.setDescription(data.getString("description"));
        }
        if (data.has("public")) {
            video.setAccessible(data.getBoolean("public"));
        }
        if (data.has("panoramic")) {
            video.setPanoramic(data.getBoolean("panoramic"));
        }
        if (data.has("publishedAt")) {
            video.setPublishedAt(data.getString("publishedAt"));
        }
        if (data.has("tags")) {
            JSONArray jsonArray = data.getJSONArray("tags");
            ArrayList<String> tags = new ArrayList<>();
            for (int j = 0; j < jsonArray.length(); j++) {
                tags.add(jsonArray.getString(j));
            }
            video.setTags(tags);
        }
        if (data.has("metadata")) {
            JSONArray jsonArray = data.getJSONArray("metadata");
            Map<String, String> metadata = new HashMap<>();
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject jsonObject = jsonArray.getJSONObject(j);

                String key = jsonObject.getString("key");
                String value = jsonObject.getString("value");
                metadata.put(key, value);
            }
            video.setMetadata(metadata);

        }
        if (data.has("source")) {
            JSONObject src = data.getJSONObject("source");
            source.setType(src.getString("type"));
            source.setUri(src.getString("uri"));
            video.setSource(source);
        }
        if (data.has("assets")) {
            JSONObject asset = data.getJSONObject("assets");
            assets.setIframe(asset.getString("iframe"));
            assets.setPlayer(asset.getString("player"));
            assets.setHls(asset.getString("hls"));
            assets.setThumbnail(asset.getString("thumbnail"));
            video.setAssets(assets);
        }
        if (data.has("playerId")) {
            video.setPlayerId(data.getString("playerId"));
        }
        return video;
    }

    public List<Video> deserialize(JSONArray data) throws JSONException {
        ArrayList<Video> vidoes = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            vidoes.add(deserialize(data.getJSONObject(i)));
        }
        return vidoes;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public JSONObject serialize(Video video) throws JSONException {
        JSONObject data = new JSONObject();
        if (video.getTitle() != null) {
            data.put("title", video.getTitle());
        }
        if (video.getDescription() != null) {
            data.put("description", video.getDescription());
        }
        if (video.isAccessible() != null) {
            data.put("public", video.isAccessible());
        }
        if (video.isPanoramic() != null) {
            data.put("panoramic", video.isPanoramic());
        }
        if (video.getTags() != null) {
            JSONArray tagArray = new JSONArray();
            for (int i = 0; i < video.getTags().size(); i++) {
                tagArray.put(video.getTags().get(i));
            }
            data.put("tags", tagArray);
        }
        if (video.getMetadata() != null) {
            Map<String, String> videoMetadata = video.getMetadata();
            JSONArray metadataArray = new JSONArray();
            for (Map.Entry<String, String> e : videoMetadata.entrySet()) {
                HashMap<Object, Object> hashMap = new HashMap<>();
                hashMap.put("key", e.getKey());
                hashMap.put("value", e.getValue());
                metadataArray.put(new JSONObject(hashMap));
            }
            data.put("metadata", metadataArray);
        }
        if (video.getPlayerId() != null) {
            data.put("playerId", video.getPlayerId());
        }
        return data;
    }


}
