package video.api.android.sdk.infrastructure.volley;

import video.api.android.sdk.domain.exceptions.ClientException;
import video.api.android.sdk.infrastructure.volley.request.RequestBuilder;

public class TestRequestExecutor implements RequestExecutor {
    private RequestBuilder requestBuilder = null;

    @Override
    public void execute(RequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;

        try {
            requestBuilder.getConstructor().call();

        } catch (ClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public RequestBuilder getRequestBuilder() {
        return requestBuilder;
    }
}
