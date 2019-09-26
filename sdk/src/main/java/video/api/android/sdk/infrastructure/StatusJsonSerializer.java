package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import video.api.android.sdk.domain.EncodingVideo;
import video.api.android.sdk.domain.Ingest;
import video.api.android.sdk.domain.MetadataEncodingVideo;
import video.api.android.sdk.domain.Quality;
import video.api.android.sdk.domain.ReceivedBytes;
import video.api.android.sdk.domain.Status;

public class StatusJsonSerializer implements JsonSerializer<Status> {

    public Status deserialize(JSONObject response) throws JSONException {
        Status status = new Status();
        Ingest ingest = new Ingest();
        EncodingVideo encoding = new EncodingVideo();

        if (response.has("ingest")) {
            JSONObject src = response.getJSONObject("ingest");
            ingest.setStatus(src.getString("status"));
            ingest.setFilesize(src.getInt("filesize"));
            JSONArray jsonArray = src.getJSONArray("receivedBytes");
            ArrayList<ReceivedBytes> arrayList = new ArrayList<>();
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject jsonObject = jsonArray.getJSONObject(j);
                ReceivedBytes receivedBytes = new ReceivedBytes();
                receivedBytes.setTo(jsonObject.getInt("to"));
                receivedBytes.setFrom(jsonObject.getInt("from"));
                receivedBytes.setTotal(jsonObject.getInt("total"));
                arrayList.add(receivedBytes);
            }
            ingest.setReceivedBytes(arrayList);
            status.setIngest(ingest);
        }
        if (response.has("encoding")) {
            JSONObject enc = response.getJSONObject("encoding");

            encoding.setPlayable(enc.getBoolean("playable"));
            JSONArray jsonArray = enc.getJSONArray("qualities");

            ArrayList<Quality> arrayList = new ArrayList<>();
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject jsonObject = jsonArray.getJSONObject(j);
                Quality quality = new Quality();
                quality.setQuality(jsonObject.getString("quality"));
                quality.setStatus(jsonObject.getString("status"));
                arrayList.add(quality);
            }
            encoding.setQualities(arrayList);
            JSONObject metadata = enc.getJSONObject("metadata");
            MetadataEncodingVideo metadataEncodingVideo = new MetadataEncodingVideo();
            if (!metadata.isNull("width")) {
                metadataEncodingVideo.setWidth(metadata.getInt("width"));
            }
            if (!metadata.isNull("height")) {
                metadataEncodingVideo.setHeight(metadata.getInt("height"));
            }
            if (!metadata.isNull("bitrate")) {
                metadataEncodingVideo.setBitrate(metadata.getDouble("bitrate"));
            }
            if (!metadata.isNull("duration")) {
                metadataEncodingVideo.setDuration(metadata.getInt("duration"));
            }
            if (!metadata.isNull("framerate")) {
                metadataEncodingVideo.setFramerate(metadata.getLong("framerate"));
            }
            if (!metadata.isNull("samplerate")) {
                metadataEncodingVideo.setSamplerate(metadata.getLong("samplerate"));
            }
            if (!metadata.isNull("videoCodec")) {
                metadataEncodingVideo.setVideoCodec(metadata.getString("videoCodec"));
            }
            if (!metadata.isNull("audioCodec")) {
                metadataEncodingVideo.setAudioCodec(metadata.getString("audioCodec"));
            }
            if (!metadata.isNull("aspectRatio")) {
                metadataEncodingVideo.setAspectRatio(metadata.getString("aspectRatio"));
            }
            encoding.setMetadata(metadataEncodingVideo);
            status.setEncoding(encoding);
        }
        return status;
    }

    public List<Status> deserialize(JSONArray data) {
        throw new RuntimeException("Not supported");
    }

    public JSONObject serialize(Status object) {
        throw new RuntimeException("Not supported");
    }
}
