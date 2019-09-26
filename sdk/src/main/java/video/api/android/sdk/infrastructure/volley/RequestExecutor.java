package video.api.android.sdk.infrastructure.volley;

import video.api.android.sdk.infrastructure.volley.request.RequestBuilder;

public interface RequestExecutor {
    void execute(RequestBuilder requestBuilder);
}
