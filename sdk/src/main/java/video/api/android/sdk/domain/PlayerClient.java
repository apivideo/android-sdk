package video.api.android.sdk.domain;

import org.json.JSONException;

import video.api.android.sdk.domain.pagination.Pager;

public interface PlayerClient {

    void create(Player player, ResponseListener<Player> playerResponseListener, ResponseErrorListener responseErrorListener) throws JSONException;

    void update(Player player, ResponseListener<Player> playerResponseListener, ResponseErrorListener responseErrorListener) throws JSONException;

    void get(String playerId, ResponseListener<Player> playerResponseListener, ResponseErrorListener responseErrorListener);

    void delete(String playerId, EmptyResponseListener emptyResponseListener, ResponseErrorListener responseErrorListener);

    Pager<Player> list(int pageSize);

    Pager<Player> list();

    //limit width or height (max: 100px)
    //limit size (max 10kB)

    void uploadLogo(String playerId, String logoPath, String link, ResponseListener<Player> playerResponseListener, ResponseErrorListener responseErrorListener);

}
