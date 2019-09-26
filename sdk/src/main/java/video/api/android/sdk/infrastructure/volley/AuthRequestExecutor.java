package video.api.android.sdk.infrastructure.volley;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.request.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import video.api.android.sdk.domain.exceptions.ClientException;
import video.api.android.sdk.infrastructure.volley.request.RequestBuilder;

public class AuthRequestExecutor implements RequestExecutor {
    private final RequestQueue               requestQueue;
    private final LinkedList<RequestBuilder> requestBuilderQueue        = new LinkedList<>();
    private       String                     accessToken;
    private       boolean                    accessTokenRequestRequired = true;
    private       String                     refreshToken;
    private final String                     baseUri;
    private final String                     apiKey;

    public AuthRequestExecutor(RequestQueue requestQueue, String baseUri, String apiKey) {
        this.requestQueue = requestQueue;
        this.baseUri = baseUri;
        this.apiKey = apiKey;
    }

    @Override
    public void execute(final RequestBuilder requestBuilder) {
        requestBuilderQueue.addLast(requestBuilder);
        if (accessTokenRequestRequired) {
            accessTokenRequestRequired = false;
            loadAccessTokenFromApiKey(requestBuilder.getErrorListener());
        }

        flushAuthRequestQueue();
    }

    private void loadAccessTokenFromApiKey(final Response.ErrorListener errorListener) {
        Map<String, String> jsonParams = new HashMap<>();
        jsonParams.put("apiKey", apiKey);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                baseUri + "/auth/api-key",
                new JSONObject(jsonParams),
                onAccessTokenResponse(),
                errorListener
        );
        requestQueue.add(request);
    }

    private void loadAccessTokenFromRefreshToken(Response.ErrorListener errorListener) {
        Map<String, String> jsonParams = new HashMap<>();
        jsonParams.put("refreshToken", refreshToken);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                baseUri + "/auth/refresh",
                new JSONObject(jsonParams),
                onAccessTokenResponse(),
                errorListener
        );
        int socketTimeout = 6000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        requestQueue.add(request);
    }

    private Response.Listener<JSONObject> onAccessTokenResponse() {
        return response -> {
            try {
                accessToken = response.getString("access_token");
                refreshToken = response.getString("refresh_token");
                flushAuthRequestQueue();
            } catch (JSONException ignored) {

            }
        };
    }

    private void flushAuthRequestQueue() {
        if (accessToken == null) {
            return;
        }
        RequestBuilder requestBuilder;
        while ((requestBuilder = requestBuilderQueue.pollFirst()) != null) {
            try {
                executeAuthenticatedRequest(requestBuilder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void executeAuthenticatedRequest(final RequestBuilder requestBuilder) throws ClientException {
        requestBuilder.addHeader("Authorization", "Bearer " + accessToken);
        final Response.ErrorListener originalErrorListener = requestBuilder.getErrorListener();

        Response.ErrorListener authErrorListener = error -> {
            if (error.networkResponse.statusCode == 401) {
                accessToken = null;
                requestBuilderQueue.addLast(requestBuilder);
                loadAccessTokenFromRefreshToken(requestBuilder.getErrorListener());
            } else {
                originalErrorListener.onErrorResponse(error);
            }
        };

        requestBuilder.setErrorListener(authErrorListener);
        Request request = requestBuilder
                .setUrl(baseUri + requestBuilder.getUrl())
                .build();
        int socketTimeout = 6000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        requestQueue.add(request);
    }

}
