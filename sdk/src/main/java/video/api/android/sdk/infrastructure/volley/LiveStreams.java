package video.api.android.sdk.infrastructure.volley;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import video.api.android.sdk.domain.EmptyResponseListener;
import video.api.android.sdk.domain.LiveStream;
import video.api.android.sdk.domain.LiveStreamClient;
import video.api.android.sdk.domain.ResponseErrorListener;
import video.api.android.sdk.domain.ResponseListener;
import video.api.android.sdk.domain.exceptions.ClientException;
import video.api.android.sdk.domain.exceptions.ServerException;
import video.api.android.sdk.domain.pagination.PageLoader;
import video.api.android.sdk.domain.pagination.PageResponseListener;
import video.api.android.sdk.domain.pagination.Pager;
import video.api.android.sdk.infrastructure.LiveStreamJsonSerializer;
import video.api.android.sdk.infrastructure.PageJsonSerializer;
import video.api.android.sdk.infrastructure.volley.request.RequestBuilder;

public class LiveStreams implements LiveStreamClient, PageLoader<LiveStream> {

    private RequestExecutor                requestExecutor;
    private LiveStreamJsonSerializer       liveStreamJsonSerializer;
    private PageJsonSerializer<LiveStream> livePageJsonSerializer;


    public LiveStreams(RequestExecutor requestExecutor, LiveStreamJsonSerializer liveStreamJsonSerializer, PageJsonSerializer<LiveStream> livePageJsonSerializer) {
        this.requestExecutor = requestExecutor;
        this.liveStreamJsonSerializer = liveStreamJsonSerializer;
        this.livePageJsonSerializer = livePageJsonSerializer;
    }

    public void create(LiveStream liveStream, ResponseListener<LiveStream> liveResponseListener, ResponseErrorListener responseErrorListener) throws JSONException {
        RequestBuilder requestBuilder = new RequestBuilder()
                .jsonRequest(liveStreamJsonSerializer.serialize(liveStream), response -> {
                    try {
                        liveResponseListener.onResponse(liveStreamJsonSerializer.deserialize(response));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.POST)
                .setUrl("/live-streams")
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public void update(LiveStream liveStream, ResponseListener<LiveStream> liveResponseListener, ResponseErrorListener responseErrorListener) throws JSONException {
        RequestBuilder requestBuilder = new RequestBuilder()
                .jsonRequest(liveStreamJsonSerializer.serialize(liveStream), response -> {
                    try {
                        liveResponseListener.onResponse(liveStreamJsonSerializer.deserialize(response));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.PATCH)
                .setUrl("/live-streams/" + liveStream.getLiveStreamId())
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public void uploadThumbnail(final String liveStreamId, String thumbnailPath, ResponseListener<LiveStream> liveResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .mutipartRequest(thumbnailPath, response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        liveResponseListener.onResponse(liveStreamJsonSerializer.deserialize(jsonObject));

                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.POST)
                .setUrl("/live-streams/" + liveStreamId + "/thumbnail")
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public Pager<LiveStream> list(int pageSize) {
        return new Pager<>(this, pageSize);
    }

    public Pager<LiveStream> list() {
        return list(25);
    }

    public void loadPage(int page, int pageSize, PageResponseListener<LiveStream> listener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        listener.onResponse(livePageJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.GET)
                .setUrl("/live-streams?currentPage=" + page + "&pageSize=" + pageSize)
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);

    }

    public void get(final String liveStreamId, ResponseListener<LiveStream> liveResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        liveResponseListener.onResponse(liveStreamJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.GET)
                .setUrl("/live-streams/" + liveStreamId)
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public void delete(final String liveStreamId, EmptyResponseListener emptyResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> emptyResponseListener.onResponse())
                .setMethod(Request.Method.DELETE)
                .setUrl("/live-streams/" + liveStreamId)
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public void deleteThumbnail(final String liveStreamId, ResponseListener<LiveStream> liveResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        liveResponseListener.onResponse(liveStreamJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.DELETE)
                .setUrl("/live-streams/" + liveStreamId + "/thumbnail")
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }


}
