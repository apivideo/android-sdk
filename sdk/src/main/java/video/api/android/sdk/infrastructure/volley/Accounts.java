package video.api.android.sdk.infrastructure.volley;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import video.api.android.sdk.domain.Account;
import video.api.android.sdk.domain.AccountClient;
import video.api.android.sdk.domain.ResponseErrorListener;
import video.api.android.sdk.domain.ResponseListener;
import video.api.android.sdk.domain.exceptions.ClientException;
import video.api.android.sdk.domain.exceptions.ServerException;
import video.api.android.sdk.infrastructure.AccountJsonSerializer;
import video.api.android.sdk.infrastructure.volley.request.RequestBuilder;

public class Accounts implements AccountClient {

    private RequestExecutor       requestExecutor;
    private AccountJsonSerializer accountJsonSerializer;


    public Accounts(RequestExecutor requestExecutor, AccountJsonSerializer accountJsonSerializer) {
        this.requestExecutor = requestExecutor;
        this.accountJsonSerializer = accountJsonSerializer;
    }

    public void get(final ResponseListener<Account> responseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        responseListener.onResponse(accountJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.GET)
                .setUrl("/account")
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
