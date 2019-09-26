package video.api.android.sdk.domain;

import org.json.JSONException;

import java.util.ArrayList;

public interface CaptionClient {

    void upload(String videoId, String language, String captionPath, ResponseListener<Caption> captionResponseListener, ResponseErrorListener responseErrorListener);

    void get(String videoId, String language, ResponseListener<Caption> captionResponseListener, ResponseErrorListener responseErrorListener);

    void getAll(String videoId, ResponseListener<ArrayList<Caption>> captionsResponseListener, ResponseErrorListener responseErrorListener);

    void update(String videoId, String language, Caption caption, ResponseListener<Caption> captionResponseListener, ResponseErrorListener responseErrorListener) throws JSONException;

    void delete(String videoId, String language, EmptyResponseListener emptyResponseListener, ResponseErrorListener responseErrorListener);

}
