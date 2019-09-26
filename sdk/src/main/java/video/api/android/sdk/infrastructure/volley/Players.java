package video.api.android.sdk.infrastructure.volley;


import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import video.api.android.sdk.domain.EmptyResponseListener;
import video.api.android.sdk.domain.Player;
import video.api.android.sdk.domain.PlayerClient;
import video.api.android.sdk.domain.ResponseErrorListener;
import video.api.android.sdk.domain.ResponseListener;
import video.api.android.sdk.domain.exceptions.ClientException;
import video.api.android.sdk.domain.exceptions.ServerException;
import video.api.android.sdk.domain.pagination.PageLoader;
import video.api.android.sdk.domain.pagination.PageResponseListener;
import video.api.android.sdk.domain.pagination.Pager;
import video.api.android.sdk.infrastructure.PageJsonSerializer;
import video.api.android.sdk.infrastructure.PlayerJsonSerializer;
import video.api.android.sdk.infrastructure.volley.request.RequestBuilder;

public class Players implements PlayerClient, PageLoader<Player> {

    private RequestExecutor            requestExecutor;
    private PlayerJsonSerializer       playerJsonSerializer;
    private PageJsonSerializer<Player> playerPageJsonSerializer;


    public Players(RequestExecutor requestExecutor, PlayerJsonSerializer playerJsonSerializer, PageJsonSerializer<Player> playerPageJsonSerializer) {
        this.requestExecutor = requestExecutor;
        this.playerJsonSerializer = playerJsonSerializer;
        this.playerPageJsonSerializer = playerPageJsonSerializer;
    }

    public void create(Player player, ResponseListener<Player> playerResponseListener, ResponseErrorListener responseErrorListener) throws JSONException {
        RequestBuilder requestBuilder = new RequestBuilder()
                .jsonRequest(playerJsonSerializer.serialize(player), response -> {
                    try {
                        playerResponseListener.onResponse(playerJsonSerializer.deserialize(response));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.POST)
                .setUrl("/players")
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public void update(Player player, ResponseListener<Player> playerResponseListener, ResponseErrorListener responseErrorListener) throws JSONException {
        RequestBuilder requestBuilder = new RequestBuilder()
                .jsonRequest(playerJsonSerializer.serialize(player), response -> {
                    try {
                        playerResponseListener.onResponse(playerJsonSerializer.deserialize(response));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.PATCH)
                .setUrl("/players/" + player.getPlayerId())
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public void get(String playerId, ResponseListener<Player> playerResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        playerResponseListener.onResponse(playerJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.GET)
                .setUrl("/players/" + playerId)
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public void delete(String playerId, EmptyResponseListener emptyResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> emptyResponseListener.onResponse())
                .setMethod(Request.Method.DELETE)
                .setUrl("/players/" + playerId)
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public Pager<Player> list(int pageSize) {
        return new Pager<>(this, pageSize);
    }

    public Pager<Player> list() {
        return list(25);
    }

    public void loadPage(int page, int pageSize, PageResponseListener<Player> listener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        listener.onResponse(playerPageJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.GET)
                .setUrl("/players?currentPage=" + page + "&pageSize=" + pageSize)
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    //limit width or height (max: 100px)
    //limit size (max 10kB)
    public void uploadLogo(String playerId, String logoPath, String link, ResponseListener<Player> playerResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .mutipartRequest(logoPath, link, response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        playerResponseListener.onResponse(playerJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.POST)
                .setUrl("/players/" + playerId + "/logo")
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
