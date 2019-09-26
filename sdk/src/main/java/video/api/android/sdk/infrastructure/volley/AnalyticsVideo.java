package video.api.android.sdk.infrastructure.volley;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import video.api.android.sdk.domain.AnalyticsVideoClient;
import video.api.android.sdk.domain.ResponseErrorListener;
import video.api.android.sdk.domain.ResponseListener;
import video.api.android.sdk.domain.analytic.AnalyticVideo;
import video.api.android.sdk.domain.exceptions.ClientException;
import video.api.android.sdk.domain.exceptions.ServerException;
import video.api.android.sdk.domain.pagination.AnalyticsFilter;
import video.api.android.sdk.domain.pagination.PageLoaderAnalytic;
import video.api.android.sdk.domain.pagination.PageResponseListener;
import video.api.android.sdk.domain.pagination.Pager;
import video.api.android.sdk.domain.pagination.PagerBuilder;
import video.api.android.sdk.infrastructure.AnalyticVideoJsonSerializer;
import video.api.android.sdk.infrastructure.PageJsonSerializer;
import video.api.android.sdk.infrastructure.volley.request.RequestBuilder;

public class AnalyticsVideo implements AnalyticsVideoClient, PageLoaderAnalytic<AnalyticVideo> {

    private RequestExecutor                   requestExecutor;
    private AnalyticVideoJsonSerializer       analyticVideoJsonSerializer;
    private PageJsonSerializer<AnalyticVideo> analyticVideoPageJsonSerializer;


    public AnalyticsVideo(RequestExecutor requestExecutor, AnalyticVideoJsonSerializer analyticVideoJsonSerializer, PageJsonSerializer<AnalyticVideo> analyticVideoPageJsonSerializer) {
        this.requestExecutor = requestExecutor;
        this.analyticVideoJsonSerializer = analyticVideoJsonSerializer;
        this.analyticVideoPageJsonSerializer = analyticVideoPageJsonSerializer;
    }

    //  For a day : 2018-01-01,
//  For a week: 2018-W01,
//  For a month: 2018-01
//  For a year: 2018
//
//  For a range period:
//
//  Date range: 2018-01-01/2018-01-15
//  Week range: 2018-W01/2018-W03
//  Month range: 2018-01/2018-03
//  Year range: 2018/2020
    public void getVideoAnalytics(String videoId, String period, ResponseListener<AnalyticVideo> analyticsVideoResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        analyticsVideoResponseListener.onResponse(analyticVideoJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(com.android.volley.Request.Method.GET)
                .setUrl("/analytics/videos/" + videoId + "?period=" + period)
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public Pager<AnalyticVideo> search(String period, PagerBuilder pagerBuilder, AnalyticsFilter analyticsFilter) {
        return new Pager<>(this, period, pagerBuilder, analyticsFilter);
    }

    public Pager<AnalyticVideo> search(String period, AnalyticsFilter analyticsFilter) {
        return search(period, new PagerBuilder(), analyticsFilter);
    }

    public Pager<AnalyticVideo> list(String period, PagerBuilder pagerBuilder) {
        return search(period, pagerBuilder, new AnalyticsFilter());
    }

    public Pager<AnalyticVideo> list(String period) {
        return list(period, new PagerBuilder());
    }

    public void loadPageOfProjectAnalytics(String period, int page, PagerBuilder pagerBuilder, AnalyticsFilter analyticsFilter, PageResponseListener<AnalyticVideo> listener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        listener.onResponse(analyticVideoPageJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.GET)
                .setUrl("/analytics/videos/?period=" + period + "&currentPage=" + page + pagerBuilder.build() + analyticsFilter.build())
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
