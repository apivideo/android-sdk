package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import video.api.android.sdk.domain.Status;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class StatusJsonSerializerTest {
    private JsonSerializer<Status> statusJsonSerializer;

    @Before
    public void setUp() {
        statusJsonSerializer = new StatusJsonSerializer();
    }

    @Test
    public void deserializeEmpty() throws JSONException {
        assertNull(statusJsonSerializer.deserialize(new JSONObject()).getEncoding());
    }

    @Test(expected = RuntimeException.class)
    public void deserializeAllFailure() throws JSONException {
        statusJsonSerializer.deserialize(new JSONArray());
    }

    @Test(expected = RuntimeException.class)
    public void serializeFailure() throws JSONException {
        statusJsonSerializer.serialize(new Status());
    }

    @Test
    public void deserializeMinimalSuccess() throws JSONException {
        Map<Object, Object> jsonParams = new HashMap<>();
        Map<Object, Object> ingestParams = new HashMap<>();
        Map<Object, Object> encodingParams = new HashMap<>();

        ingestParams.put("status", "uploaded");
        ingestParams.put("filesize", 2145758);
        ingestParams.put("receivedBytes", new JSONArray());

        encodingParams.put("playable", true);

        JSONArray qualities = new JSONArray() {
        };
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("quality", "360p");
        hashMap.put("status", "encoded");
        qualities.put(new JSONObject(hashMap));

        encodingParams.put("qualities", qualities);

        Map<Object, Object> metadataParams = new HashMap<>();
        metadataParams.put("width", 600);
        metadataParams.put("height", 300);
        metadataParams.put("bitrate", 254.242);
        metadataParams.put("duration", 48);
        metadataParams.put("framerate", 30);
        metadataParams.put("samplerate", 44100);
        metadataParams.put("videoCodec", "h264");
        metadataParams.put("audioCodec", "aac");
        metadataParams.put("aspectRatio", "16:9");

        encodingParams.put("metadata", new JSONObject(metadataParams));


        jsonParams.put("ingest", new JSONObject(ingestParams));
        jsonParams.put("encoding", new JSONObject(encodingParams));

        Status status = statusJsonSerializer.deserialize(new JSONObject(jsonParams));
        assertEquals("uploaded", status.getIngest().getStatus());
        assertEquals(2145758, status.getIngest().getFilesize());
        assertTrue(status.getIngest().getReceivedBytes().isEmpty());

        assertTrue(status.getEncoding().isPlayable());
        assertEquals("360p", status.getEncoding().getQualities().get(0).getQuality());
        assertEquals("encoded", status.getEncoding().getQualities().get(0).getStatus());
        assertEquals(600, status.getEncoding().getMetadata().getWidth());
        assertEquals(300, status.getEncoding().getMetadata().getHeight());
        assertEquals(new Double(254.242), status.getEncoding().getMetadata().getBitrate());
        assertEquals(48, status.getEncoding().getMetadata().getDuration());
        assertEquals(30, status.getEncoding().getMetadata().getFramerate());
        assertEquals(44100, status.getEncoding().getMetadata().getSamplerate());
        assertEquals("h264", status.getEncoding().getMetadata().getVideoCodec());
        assertEquals("aac", status.getEncoding().getMetadata().getAudioCodec());
        assertEquals("16:9", status.getEncoding().getMetadata().getAspectRatio());
    }

    @Test
    public void deserializeMaximalSuccess() throws JSONException {
        Map<Object, Object> jsonParams = new HashMap<>();
        Map<Object, Object> ingestParams = new HashMap<>();
        Map<Object, Object> encodingParams = new HashMap<>();

        ingestParams.put("status", "uploaded");
        ingestParams.put("filesize", 2145758);

        JSONArray receivedBytes = new JSONArray() {
        };
        HashMap<Object, Object> hashMap1 = new HashMap<>();
        hashMap1.put("to", 1048576);
        hashMap1.put("from", 0);
        hashMap1.put("total", 2145758);
        HashMap<Object, Object> hashMap2 = new HashMap<>();
        hashMap2.put("to", 2097153);
        hashMap2.put("from", 1048577);
        hashMap2.put("total", 2145758);
        HashMap<Object, Object> hashMap3 = new HashMap<>();
        hashMap3.put("to", 2145757);
        hashMap3.put("from", 2097154);
        hashMap3.put("total", 2145758);

        receivedBytes.put(new JSONObject(hashMap1));
        receivedBytes.put(new JSONObject(hashMap2));
        receivedBytes.put(new JSONObject(hashMap3));

        ingestParams.put("receivedBytes", receivedBytes);

        encodingParams.put("playable", true);

        JSONArray qualities = new JSONArray() {
        };
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("quality", "360p");
        hashMap.put("status", "encoded");
        qualities.put(new JSONObject(hashMap));

        encodingParams.put("qualities", qualities);

        Map<Object, Object> metadataParams = new HashMap<>();
        metadataParams.put("width", 600);
        metadataParams.put("height", 300);
        metadataParams.put("bitrate", 254.242);
        metadataParams.put("duration", 48);
        metadataParams.put("framerate", 30);
        metadataParams.put("samplerate", 44100);
        metadataParams.put("videoCodec", "h264");
        metadataParams.put("audioCodec", "aac");
        metadataParams.put("aspectRatio", "16:9");

        encodingParams.put("metadata", new JSONObject(metadataParams));


        jsonParams.put("ingest", new JSONObject(ingestParams));
        jsonParams.put("encoding", new JSONObject(encodingParams));

        Status status = statusJsonSerializer.deserialize(new JSONObject(jsonParams));
        assertEquals("uploaded", status.getIngest().getStatus());
        assertEquals(2145758, status.getIngest().getFilesize());
        assertEquals(1048576, status.getIngest().getReceivedBytes().get(0).getTo());
        assertEquals(0, status.getIngest().getReceivedBytes().get(0).getFrom());
        assertEquals(2145758, status.getIngest().getReceivedBytes().get(0).getTotal());
        assertEquals(2097153, status.getIngest().getReceivedBytes().get(1).getTo());
        assertEquals(1048577, status.getIngest().getReceivedBytes().get(1).getFrom());
        assertEquals(2145758, status.getIngest().getReceivedBytes().get(1).getTotal());
        assertEquals(2145757, status.getIngest().getReceivedBytes().get(2).getTo());
        assertEquals(2097154, status.getIngest().getReceivedBytes().get(2).getFrom());
        assertEquals(2145758, status.getIngest().getReceivedBytes().get(2).getTotal());
        assertTrue(status.getEncoding().isPlayable());
        assertEquals("360p", status.getEncoding().getQualities().get(0).getQuality());
        assertEquals("encoded", status.getEncoding().getQualities().get(0).getStatus());
        assertEquals(600, status.getEncoding().getMetadata().getWidth());
        assertEquals(300, status.getEncoding().getMetadata().getHeight());
        assertEquals(new Double(254.242), status.getEncoding().getMetadata().getBitrate());
        assertEquals(48, status.getEncoding().getMetadata().getDuration());
        assertEquals(30, status.getEncoding().getMetadata().getFramerate());
        assertEquals(44100, status.getEncoding().getMetadata().getSamplerate());
        assertEquals("h264", status.getEncoding().getMetadata().getVideoCodec());
        assertEquals("aac", status.getEncoding().getMetadata().getAudioCodec());
        assertEquals("16:9", status.getEncoding().getMetadata().getAspectRatio());
    }

}