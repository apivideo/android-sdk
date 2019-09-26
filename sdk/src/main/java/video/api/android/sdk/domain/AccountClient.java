package video.api.android.sdk.domain;

public interface AccountClient {

    void get(ResponseListener<Account> responseListener, ResponseErrorListener responseErrorListener);

}
