package video.api.android.sdk.infrastructure.volley;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import video.api.android.sdk.domain.ResponseErrorListener;
import video.api.android.sdk.domain.ResponseListener;
import video.api.android.sdk.domain.Token;
import video.api.android.sdk.domain.TokenClient;
import video.api.android.sdk.domain.exceptions.ClientException;
import video.api.android.sdk.domain.exceptions.ServerException;
import video.api.android.sdk.infrastructure.TokenJsonSerializer;
import video.api.android.sdk.infrastructure.volley.request.RequestBuilder;

public class Tokens implements TokenClient {

    private RequestExecutor     requestExecutor;
    private TokenJsonSerializer tokenJsonSerializer;

    public Tokens(RequestExecutor requestExecutor, TokenJsonSerializer tokenJsonSerializer) {
        this.requestExecutor = requestExecutor;
        this.tokenJsonSerializer = tokenJsonSerializer;
    }

    public void generate(ResponseListener<Token> tokenResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        tokenResponseListener.onResponse(tokenJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.POST)
                .setUrl("/tokens")
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
