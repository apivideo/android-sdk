package video.api.android.sdk;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import video.api.android.sdk.infrastructure.AccountJsonSerializer;
import video.api.android.sdk.infrastructure.AnalyticEventJsonSerializer;
import video.api.android.sdk.infrastructure.AnalyticLiveJsonSerializer;
import video.api.android.sdk.infrastructure.AnalyticVideoJsonSerializer;
import video.api.android.sdk.infrastructure.CaptionJsonSerializer;
import video.api.android.sdk.infrastructure.LiveStreamJsonSerializer;
import video.api.android.sdk.infrastructure.PageJsonSerializer;
import video.api.android.sdk.infrastructure.PlayerJsonSerializer;
import video.api.android.sdk.infrastructure.StatusJsonSerializer;
import video.api.android.sdk.infrastructure.TokenJsonSerializer;
import video.api.android.sdk.infrastructure.VideoJsonSerializer;
import video.api.android.sdk.infrastructure.volley.Accounts;
import video.api.android.sdk.infrastructure.volley.AnalyticsEvent;
import video.api.android.sdk.infrastructure.volley.AnalyticsLive;
import video.api.android.sdk.infrastructure.volley.AnalyticsVideo;
import video.api.android.sdk.infrastructure.volley.AuthRequestExecutor;
import video.api.android.sdk.infrastructure.volley.Captions;
import video.api.android.sdk.infrastructure.volley.LiveStreams;
import video.api.android.sdk.infrastructure.volley.Players;
import video.api.android.sdk.infrastructure.volley.RequestExecutor;
import video.api.android.sdk.infrastructure.volley.Tokens;
import video.api.android.sdk.infrastructure.volley.Videos;

public class ClientFactory {
    public Client create(Context context, String apiKey) {
        return create(context, "https://ws.api.video", apiKey);
    }

    public Client createSandbox(Context context, String apiKey) {
        return create(context, "https://sandbox.api.video", apiKey);
    }

    private Client create(Context context, String baseUri, String apiKey) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        RequestExecutor requestExecutor = new AuthRequestExecutor(requestQueue, baseUri, apiKey);

        VideoJsonSerializer videoJsonSerializer = new VideoJsonSerializer();
        LiveStreamJsonSerializer liveStreamJsonSerializer = new LiveStreamJsonSerializer();
        PlayerJsonSerializer playerJsonSerializer = new PlayerJsonSerializer();
        AnalyticVideoJsonSerializer analyticVideoJsonSerializer = new AnalyticVideoJsonSerializer();
        AnalyticLiveJsonSerializer analyticLiveJsonSerializer = new AnalyticLiveJsonSerializer();
        AnalyticEventJsonSerializer analyticEventJsonSerializer = new AnalyticEventJsonSerializer();

        return new Client(
                new Videos(requestExecutor, videoJsonSerializer, new StatusJsonSerializer(), new PageJsonSerializer<>(videoJsonSerializer)),
                new LiveStreams(requestExecutor, liveStreamJsonSerializer, new PageJsonSerializer<>(liveStreamJsonSerializer)),
                new Captions(requestExecutor, new CaptionJsonSerializer()),
                new Players(requestExecutor, playerJsonSerializer, new PageJsonSerializer<>(playerJsonSerializer)),
                new Accounts(requestExecutor, new AccountJsonSerializer()),
                new Tokens(requestExecutor, new TokenJsonSerializer()),
                new AnalyticsVideo(requestExecutor, analyticVideoJsonSerializer, new PageJsonSerializer<>(analyticVideoJsonSerializer)),
                new AnalyticsLive(requestExecutor, analyticLiveJsonSerializer, new PageJsonSerializer<>(analyticLiveJsonSerializer)),
                new AnalyticsEvent(requestExecutor, new PageJsonSerializer<>(analyticEventJsonSerializer))
        );
    }

}


