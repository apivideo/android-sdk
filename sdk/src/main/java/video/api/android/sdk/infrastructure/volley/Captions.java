package video.api.android.sdk.infrastructure.volley;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import video.api.android.sdk.domain.Caption;
import video.api.android.sdk.domain.CaptionClient;
import video.api.android.sdk.domain.EmptyResponseListener;
import video.api.android.sdk.domain.ResponseErrorListener;
import video.api.android.sdk.domain.ResponseListener;
import video.api.android.sdk.domain.exceptions.ClientException;
import video.api.android.sdk.domain.exceptions.ServerException;
import video.api.android.sdk.infrastructure.CaptionJsonSerializer;
import video.api.android.sdk.infrastructure.volley.request.RequestBuilder;

public class Captions implements CaptionClient {

    private RequestExecutor       requestExecutor;
    private CaptionJsonSerializer captionJsonSerializer;

    public Captions(RequestExecutor requestExecutor, CaptionJsonSerializer captionJsonSerializer) {
        this.requestExecutor = requestExecutor;
        this.captionJsonSerializer = captionJsonSerializer;
    }

    //fichier d'extension .vtt
    public void upload(String videoId, String language, String captionPath, ResponseListener<Caption> captionResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .mutipartRequest(captionPath, response -> {
                    if (!response.isEmpty()) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            captionResponseListener.onResponse(captionJsonSerializer.deserialize(jsonObject));
                        } catch (JSONException e) {
                            responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                        }
                    }
                })
                .setMethod(Request.Method.POST)
                .setUrl("/videos/" + videoId + "/captions/" + language)
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public void get(String videoId, String language, ResponseListener<Caption> captionResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> {
                    if (!response.isEmpty()) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            captionResponseListener.onResponse(captionJsonSerializer.deserialize(jsonObject));
                        } catch (JSONException e) {
                            responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                        }
                    }
                })
                .setMethod(Request.Method.GET)
                .setUrl("/videos/" + videoId + "/captions/" + language)
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public void getAll(String videoId, ResponseListener<ArrayList<Caption>> captionsResponseListener, ResponseErrorListener responseErrorListener) {
        ArrayList<Caption> captionArrayList = new ArrayList<>();
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> {
                    if (!response.isEmpty()) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            captionArrayList.addAll(captionJsonSerializer.deserialize(jsonArray));
                            captionsResponseListener.onResponse(captionArrayList);
                        } catch (JSONException e) {
                            responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                        }
                    }
                })
                .setMethod(Request.Method.GET)
                .setUrl("/videos/" + videoId + "/captions")
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public void update(String videoId, String language, Caption caption, ResponseListener<Caption> captionResponseListener, ResponseErrorListener responseErrorListener) throws JSONException {
        RequestBuilder requestBuilder = new RequestBuilder()
                .jsonRequest(captionJsonSerializer.serialize(caption), response -> {
                    if (!response.toString().isEmpty()) {
                        try {
                            captionResponseListener.onResponse(captionJsonSerializer.deserialize(response));
                        } catch (JSONException e) {
                            responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                        }
                    }
                })
                .setMethod(Request.Method.PATCH)
                .setUrl("/videos/" + videoId + "/captions/" + language)
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public void delete(String videoId, String language, EmptyResponseListener emptyResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> emptyResponseListener.onResponse())
                .setMethod(Request.Method.DELETE)
                .setUrl("/videos/" + videoId + "/captions/" + language)
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
