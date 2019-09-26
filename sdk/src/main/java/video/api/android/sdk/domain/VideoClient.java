package video.api.android.sdk.domain;

import org.json.JSONException;

import video.api.android.sdk.domain.pagination.Pager;
import video.api.android.sdk.domain.pagination.PagerBuilder;
import video.api.android.sdk.domain.pagination.VideoFilter;

public interface VideoClient {

    void create(Video video, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener) throws JSONException;

    void update(Video video, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener) throws JSONException;

    void uploadThumbnail(String videoId, String thumbnailPath, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener);

    void updateThumbnailWithTimeCode(String videoId, String timecode, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener);

    //size<=80Mo
    void upload(Video video, String videoPath, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener);

    void upload(String videoPath, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener);

    void get(String videoId, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener);

    void getStatus(String videoId, ResponseListener<Status> statusResponseListener, ResponseErrorListener responseErrorListener);

    void delete(String videoId, EmptyResponseListener emptyResponseListener, ResponseErrorListener responseErrorListener);

    Pager<Video> search(PagerBuilder pagerBuilder, VideoFilter videoFilter);

    Pager<Video> search(VideoFilter videoFilter);

    Pager<Video> list(PagerBuilder pagerBuilder);

    Pager<Video> list();

    void delegatedUpload(String token, String videoPath, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener);

}
