package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import video.api.android.sdk.domain.analytic.AnalyticClient;
import video.api.android.sdk.domain.analytic.AnalyticData;
import video.api.android.sdk.domain.analytic.AnalyticDevice;
import video.api.android.sdk.domain.analytic.AnalyticLive;
import video.api.android.sdk.domain.analytic.AnalyticLocation;
import video.api.android.sdk.domain.analytic.AnalyticOs;
import video.api.android.sdk.domain.analytic.AnalyticReferrer;
import video.api.android.sdk.domain.analytic.AnalyticSession;

public class AnalyticLiveJsonSerializer implements JsonSerializer<AnalyticLive> {

    public AnalyticLive deserialize(JSONObject response) throws JSONException {
        AnalyticLive analyticLive = new AnalyticLive();
        AnalyticData analyticData = new AnalyticData();
        AnalyticSession analyticSession = new AnalyticSession();
        AnalyticLocation analyticLocation = new AnalyticLocation();
        AnalyticReferrer analyticReferrer = new AnalyticReferrer();
        AnalyticDevice analyticDevice = new AnalyticDevice();
        AnalyticOs analyticOs = new AnalyticOs();
        AnalyticClient analyticClient = new AnalyticClient();
        ArrayList<AnalyticData> analyticDataArrayList = new ArrayList<>();

        String live_stream_id = response.getJSONObject("live").getString("liveStreamId");
        String name = response.getJSONObject("live").getString("name");
        String period = response.getString("period");
        analyticLive.setLiveStreamId(live_stream_id);
        analyticLive.setLiveName(name);
        analyticLive.setPeriod(period);

        JSONArray jsonArray = response.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String session_id = jsonObject1.getJSONObject("session").getString("sessionId");
            String loaded_at = jsonObject1.getJSONObject("session").getString("loadedAt");
            String ended_at = jsonObject1.getJSONObject("session").getString("endedAt");

            analyticSession.setSessionId(session_id);
            analyticSession.setLoadedAt(loaded_at);
            analyticSession.setEndedAt(ended_at);


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

            String nameOs = jsonObject1.getJSONObject("os").getString("name");
            String shortname = jsonObject1.getJSONObject("os").getString("shortname");
            String version = jsonObject1.getJSONObject("os").getString("version");
            analyticOs.setName(nameOs);
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
        analyticLive.setData(analyticDataArrayList);
        return analyticLive;
    }

    public List<AnalyticLive> deserialize(JSONArray data) throws JSONException {
        ArrayList<AnalyticLive> analyticLives = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            analyticLives.add(deserialize(data.getJSONObject(i)));
        }
        return analyticLives;
    }

    public JSONObject serialize(AnalyticLive object) {
        throw new RuntimeException("Not supported");
    }


}
