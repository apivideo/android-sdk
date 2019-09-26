package video.api.android.sdk.domain;


public interface TokenClient {

    void generate(ResponseListener<Token> tokenResponseListener, ResponseErrorListener responseErrorListener);

}
