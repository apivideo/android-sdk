package video.api.android.sdk.domain;

import org.json.JSONException;

import video.api.android.sdk.domain.pagination.Pager;

public interface LiveStreamClient {

    void create(LiveStream liveStream, ResponseListener<LiveStream> liveResponseListener, ResponseErrorListener responseErrorListener) throws JSONException;

    void update(LiveStream liveStream, ResponseListener<LiveStream> liveResponseListener, ResponseErrorListener responseErrorListener) throws JSONException;

    void uploadThumbnail(String liveStreamId, String thumbnailPath, ResponseListener<LiveStream> liveResponseListener, ResponseErrorListener responseErrorListener);

    Pager<LiveStream> list(int pageSize);

    Pager<LiveStream> list();

    void get(String liveStreamId, ResponseListener<LiveStream> liveResponseListener, ResponseErrorListener responseErrorListener);

    void delete(String liveStreamId, EmptyResponseListener emptyResponseListener, ResponseErrorListener responseErrorListener);

    void deleteThumbnail(String liveStreamId, ResponseListener<LiveStream> liveResponseListener, ResponseErrorListener responseErrorListener);

}
