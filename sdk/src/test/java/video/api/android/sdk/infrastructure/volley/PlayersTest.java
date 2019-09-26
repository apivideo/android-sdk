package video.api.android.sdk.infrastructure.volley;

import com.android.volley.Request;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import video.api.android.sdk.domain.Player;
import video.api.android.sdk.infrastructure.PlayerJsonSerializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayersTest {
    private       Players             players;
    private final TestRequestExecutor requestExecutor = new TestRequestExecutor();

    @Before
    public void setUp() {
        players = new Players(requestExecutor, new PlayerJsonSerializer(), null);

    }

    @Test
    public void create() throws JSONException {
        Player player = new Player();
        players.create(player, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/players"));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.POST);
    }

    @Test
    public void update() throws JSONException {
        Player player = new Player();
        player.setPlayerId("playSuccess");
        players.update(player, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/players/" + player.getPlayerId()));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.PATCH);
    }

    @Test
    public void get() {
        String playerId = "playSuccess";
        players.get(playerId, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/players/" + playerId));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.GET);
    }

    @Test
    public void delete() {
        String playerId = "playSuccess";
        players.delete(playerId, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/players/" + playerId));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.DELETE);
    }

    @Test
    public void loadPage() {
        int page = 1;
        int pageSize = 50;
        players.loadPage(page, pageSize, null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/players?currentPage=" + page + "&pageSize=" + pageSize));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.GET);
    }

    @Test
    public void uploadLogo() {
        String playerId = "playSuccess";
        players.uploadLogo(playerId, "logoPath", "link", null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/players/" + playerId + "/logo"));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.POST);
    }
}