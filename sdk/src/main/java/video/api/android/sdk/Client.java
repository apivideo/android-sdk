package video.api.android.sdk;

import video.api.android.sdk.domain.AccountClient;
import video.api.android.sdk.domain.AnalyticsEventClient;
import video.api.android.sdk.domain.AnalyticsLiveClient;
import video.api.android.sdk.domain.AnalyticsVideoClient;
import video.api.android.sdk.domain.CaptionClient;
import video.api.android.sdk.domain.LiveStreamClient;
import video.api.android.sdk.domain.PlayerClient;
import video.api.android.sdk.domain.TokenClient;
import video.api.android.sdk.domain.VideoClient;

public class Client {
    public final VideoClient          videos;
    public final LiveStreamClient     liveStreams;
    public final CaptionClient        captions;
    public final PlayerClient         players;
    public final AccountClient        accounts;
    public final TokenClient          tokens;
    public final AnalyticsVideoClient videoAnalytics;
    public final AnalyticsLiveClient  liveStreamAnalytics;
    public final AnalyticsEventClient sessionEventAnalytics;

    public Client(VideoClient videos, LiveStreamClient liveStreams, CaptionClient captions, PlayerClient players, AccountClient accounts, TokenClient tokens, AnalyticsVideoClient videoAnalytics, AnalyticsLiveClient liveStreamAnalytics, AnalyticsEventClient sessionEventAnalytics) {
        this.videos = videos;
        this.liveStreams = liveStreams;
        this.captions = captions;
        this.players = players;
        this.accounts = accounts;
        this.tokens = tokens;
        this.videoAnalytics = videoAnalytics;
        this.liveStreamAnalytics = liveStreamAnalytics;
        this.sessionEventAnalytics = sessionEventAnalytics;
    }
}