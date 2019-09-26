package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import video.api.android.sdk.domain.Player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PlayerJsonSerializerTest {
    private JsonSerializer<Player> playerJsonSerializer;

    @Before
    public void setUp() {
        playerJsonSerializer = new PlayerJsonSerializer();
    }

    @Test
    public void deserializeEmpty() throws JSONException {
        assertNull(playerJsonSerializer.deserialize(new JSONObject()).getPlayerId());
    }

    @Test
    public void deserializeAllEmpty() throws JSONException {
        assertTrue(playerJsonSerializer.deserialize(new JSONArray()).isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void serializeFailure() throws JSONException {
        Player player = new Player();
        player.setBackgroundText("rgba(3000, 255, 255, .95)");
        JSONObject jsonPlayer = playerJsonSerializer.serialize(player);
        assertEquals("rgba(300, 255, 255, .95)", jsonPlayer.getString("backgroundText"));
    }

    @Test
    public void deserializeMinimalSuccess() throws JSONException {
        Map<String, String> jsonParams = new HashMap<>();
        jsonParams.put("playerId", "id");
        Player player = playerJsonSerializer.deserialize(new JSONObject(jsonParams));
        assertEquals("id", player.getPlayerId());
    }

    @Test
    public void deserializeMaximalSuccess() throws JSONException {

        Map<Object, Object> jsonParams = new HashMap<>();
        jsonParams.put("playerId", "id");
        Map<Object, Object> assetsParams = new HashMap<>();
        assetsParams.put("logo", "logo");
        assetsParams.put("link", "link");
        jsonParams.put("assets", new JSONObject(assetsParams));
        jsonParams.put("shapeMargin", 10);
        jsonParams.put("shapeRadius", 3);
        jsonParams.put("shapeAspect", "shapeAspect");
        jsonParams.put("shapeBackgroundTop", "shapeBackgroundTop");
        jsonParams.put("shapeBackgroundBottom", "shapeBackgroundBottom");
        jsonParams.put("text", "rgba(1, 2, 3, .99)");
        jsonParams.put("link", "link");
        jsonParams.put("linkHover", "linkHover");
        jsonParams.put("linkActive", "linkActive");
        jsonParams.put("trackPlayed", "trackPlayed");
        jsonParams.put("trackUnplayed", "trackUnplayed");
        jsonParams.put("trackBackground", "trackBackground");
        jsonParams.put("backgroundTop", "backgroundTop");
        jsonParams.put("backgroundBottom", "backgroundBottom");
        jsonParams.put("backgroundText", "backgroundText");
        jsonParams.put("language", "language");
        jsonParams.put("enableApi", true);
        jsonParams.put("enableControls", true);
        jsonParams.put("forceAutoplay", false);
        jsonParams.put("hideTitle", false);
        jsonParams.put("forceLoop", false);


        Player player = playerJsonSerializer.deserialize(new JSONObject(jsonParams));

        assertEquals("id", player.getPlayerId());
        assertEquals("logo", player.getAssets().getLogo());
        assertEquals("link", player.getAssets().getLink());
        assertEquals(new Integer(10), player.getShapeMargin());
        assertEquals(new Integer(3), player.getShapeRadius());
        assertEquals("shapeAspect", player.getShapeAspect());
        assertEquals("shapeBackgroundTop", player.getShapeBackgroundTop());
        assertEquals("shapeBackgroundBottom", player.getShapeBackgroundBottom());
        assertEquals("rgba(1, 2, 3, .99)", player.getText());
        assertEquals("link", player.getLink());
        assertEquals("linkHover", player.getLinkHover());
        assertEquals("linkActive", player.getLinkActive());
        assertEquals("trackPlayed", player.getTrackPlayed());
        assertEquals("trackUnplayed", player.getTrackUnplayed());
        assertEquals("trackBackground", player.getTrackBackground());
        assertEquals("backgroundTop", player.getBackgroundTop());
        assertEquals("backgroundBottom", player.getBackgroundBottom());
        assertEquals("backgroundText", player.getBackgroundText());
        assertEquals("language", player.getLanguage());
        assertEquals(true, player.isEnableApi());
        assertEquals(true, player.isEnableControls());
        assertEquals(false, player.isForceAutoplay());
        assertEquals(false, player.isHideTitle());
        assertEquals(false, player.isForceLoop());
    }

    @Test
    public void deserializeAllSuccess() throws JSONException {
        Map<String, String> jsonPlayer1 = new HashMap<>();
        jsonPlayer1.put("playerId", "player1");
        Map<String, String> jsonPlayer2 = new HashMap<>();
        jsonPlayer2.put("playerId", "player2");
        Map<String, String> jsonPlayer3 = new HashMap<>();
        jsonPlayer3.put("playerId", "player3");
        List<Player> playerList = playerJsonSerializer.deserialize(new JSONArray() {{
            put(new JSONObject(jsonPlayer1));
            put(new JSONObject(jsonPlayer2));
            put(new JSONObject(jsonPlayer3));
        }});
        assertEquals("player1", playerList.get(0).getPlayerId());
        assertEquals("player2", playerList.get(1).getPlayerId());
        assertEquals("player3", playerList.get(2).getPlayerId());
    }

    @Test
    public void serialize() throws JSONException {
        Player player = new Player();
        player.setShapeMargin(10);
        player.setBackgroundText("rgba(255, 255, 255, .95)");
        player.setEnableApi(false);
        JSONObject jsonPlayer = playerJsonSerializer.serialize(player);
        assertEquals(10, jsonPlayer.getInt("shapeMargin"));
        assertFalse(jsonPlayer.getBoolean("enableApi"));
        assertEquals("rgba(255, 255, 255, .95)", jsonPlayer.getString("backgroundText"));
    }
}