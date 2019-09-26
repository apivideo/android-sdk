package video.api.android.sdk.infrastructure.volley;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import video.api.android.sdk.domain.AnalyticsEventClient;
import video.api.android.sdk.domain.ResponseErrorListener;
import video.api.android.sdk.domain.analytic.AnalyticEvent;
import video.api.android.sdk.domain.exceptions.ClientException;
import video.api.android.sdk.domain.exceptions.ServerException;
import video.api.android.sdk.domain.pagination.PageLoader;
import video.api.android.sdk.domain.pagination.PageResponseListener;
import video.api.android.sdk.domain.pagination.Pager;
import video.api.android.sdk.infrastructure.PageJsonSerializer;
import video.api.android.sdk.infrastructure.volley.request.RequestBuilder;

public class AnalyticsEvent implements AnalyticsEventClient, PageLoader<AnalyticEvent> {

    private RequestExecutor                   requestExecutor;
    private PageJsonSerializer<AnalyticEvent> analyticEventPageJsonSerializer;
    private String                            sessionId;

    public AnalyticsEvent(RequestExecutor requestExecutor, PageJsonSerializer<AnalyticEvent> analyticEventPageJsonSerializer) {
        this.requestExecutor = requestExecutor;
        this.analyticEventPageJsonSerializer = analyticEventPageJsonSerializer;

    }

    public Pager<AnalyticEvent> list(String sessionId, int pageSize) {
        this.sessionId = sessionId;
        return new Pager<>(this, pageSize);
    }

    public Pager<AnalyticEvent> list(String sessionId) {
        return list(sessionId, 25);
    }

    public void loadPage(int page, int pageSize, PageResponseListener<AnalyticEvent> listener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        listener.onResponse(analyticEventPageJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.GET)
                .setUrl("/analytics/sessions/" + sessionId + "/events?currentPage=" + page + "&pageSize=" + pageSize)
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
