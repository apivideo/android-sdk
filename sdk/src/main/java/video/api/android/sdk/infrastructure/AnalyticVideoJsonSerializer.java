package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import video.api.android.sdk.domain.analytic.AnalyticClient;
import video.api.android.sdk.domain.analytic.AnalyticData;
import video.api.android.sdk.domain.analytic.AnalyticDevice;
import video.api.android.sdk.domain.analytic.AnalyticLocation;
import video.api.android.sdk.domain.analytic.AnalyticOs;
import video.api.android.sdk.domain.analytic.AnalyticReferrer;
import video.api.android.sdk.domain.analytic.AnalyticSession;
import video.api.android.sdk.domain.analytic.AnalyticVideo;

public class AnalyticVideoJsonSerializer implements JsonSerializer<AnalyticVideo> {

    public AnalyticVideo deserialize(JSONObject response) throws JSONException {

        AnalyticVideo analyticVideo = new AnalyticVideo();
        AnalyticData analyticData = new AnalyticData();
        AnalyticSession analyticSession = new AnalyticSession();
        AnalyticLocation analyticLocation = new AnalyticLocation();
        AnalyticReferrer analyticReferrer = new AnalyticReferrer();
        AnalyticDevice analyticDevice = new AnalyticDevice();
        AnalyticOs analyticOs = new AnalyticOs();
        AnalyticClient analyticClient = new AnalyticClient();
        ArrayList<AnalyticData> analyticDataArrayList = new ArrayList<>();

        String videoId = response.getJSONObject("video").getString("videoId");
        String title = response.getJSONObject("video").getString("title");
        JSONArray jsonArray1 = response.getJSONObject("video").getJSONArray("metadata");
        Map<String, String> metadata = new HashMap<>();
        for (int j = 0; j < jsonArray1.length(); j++) {
            JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
            String key = jsonObject1.getString("key");
            String value = jsonObject1.getString("value");
            metadata.put(key, value);
        }
        JSONArray tagsArray = response.getJSONObject("video").getJSONArray("tags");
        ArrayList<String> tags = new ArrayList<>();
        for (int j = 0; j < tagsArray.length(); j++) {
            tags.add(tagsArray.getString(j));
        }
        String period = response.getString("period");
        analyticVideo.setVideoId(videoId);
        analyticVideo.setVideoTitle(title);
        analyticVideo.setMetadata(metadata);
        analyticVideo.setPeriod(period);
        analyticVideo.setTags(tags);

        JSONArray jsonArray = response.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String session_id = jsonObject1.getJSONObject("session").getString("sessionId");
            String loaded_at = jsonObject1.getJSONObject("session").getString("loadedAt");
            String ended_at = jsonObject1.getJSONObject("session").getString("endedAt");
            JSONArray jsonArray2 = jsonObject1.getJSONObject("session").getJSONArray("metadatas");
            Map<String, String> metadatas = new HashMap<>();

            for (int j = 0; j < jsonArray2.length(); j++) {
                JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                String key = jsonObject2.getString("key");
                String value = jsonObject2.getString("value");
                metadatas.put(key, value);
            }

            analyticSession.setSessionId(session_id);
            analyticSession.setLoadedAt(loaded_at);
            analyticSession.setEndedAt(ended_at);
            analyticSession.setMetadata(metadatas);


            String country = jsonObject1.getJSONObject("location").getString("country");
            String city = jsonObject1.getJSONObject("location").getString("city");
            analyticLocation.setCountry(country);
            analyticLocation.setCity(city);

            String url = jsonObject1.getJSONObject("referrer").getString("url");
            String medium = jsonObject1.getJSONObject("referrer").getString("medium");
            String source = jsonObject1.getJSONObject("referrer").getString("source");
            String search_term = jsonObject1.getJSONObject("referrer").getString("searchTerm");
            analyticReferrer.setUrl(url);
            analyticReferrer.setMedium(medium);
            analyticReferrer.setSource(source);
            analyticReferrer.setSearch_term(search_term);

            String type = jsonObject1.getJSONObject("device").getString("type");
            String vendor = jsonObject1.getJSONObject("device").getString("vendor");
            String model = jsonObject1.getJSONObject("device").getString("model");
            analyticDevice.setType(type);
            analyticDevice.setVendor(vendor);
            analyticDevice.setModel(model);

            String name = jsonObject1.getJSONObject("os").getString("name");
            String shortname = jsonObject1.getJSONObject("os").getString("shortname");
            String version = jsonObject1.getJSONObject("os").getString("version");
            analyticOs.setName(name);
            analyticOs.setShortname(shortname);
            analyticOs.setVersion(version);

            String typeCl = jsonObject1.getJSONObject("client").getString("type");
            String nameCl = jsonObject1.getJSONObject("client").getString("name");
            String versionCl = jsonObject1.getJSONObject("client").getString("version");
            analyticClient.setType(typeCl);
            analyticClient.setName(nameCl);
            analyticClient.setVersion(versionCl);

            analyticData.setSession(analyticSession);
            analyticData.setLocation(analyticLocation);
            analyticData.setReferrer(analyticReferrer);
            analyticData.setDevice(analyticDevice);
            analyticData.setOs(analyticOs);
            analyticData.setClient(analyticClient);
            analyticDataArrayList.add(analyticData);
        }
        analyticVideo.setData(analyticDataArrayList);
        return analyticVideo;
    }

    public List<AnalyticVideo> deserialize(JSONArray data) throws JSONException {
        ArrayList<AnalyticVideo> analyticVideos = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            analyticVideos.add(deserialize(data.getJSONObject(i)));
        }
        return analyticVideos;
    }

    public JSONObject serialize(AnalyticVideo object) {
        throw new RuntimeException("Not supported");
    }

}
