package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import video.api.android.sdk.domain.AssetsPlayer;
import video.api.android.sdk.domain.Player;

public class PlayerJsonSerializer implements JsonSerializer<Player> {

    public Player deserialize(JSONObject response) throws JSONException {
        Player player = new Player();
        AssetsPlayer assets = new AssetsPlayer();

        if (response.has("playerId")) {
            player.setPlayerId(response.getString("playerId"));
        }
        if (response.has("assets")) {
            JSONObject asset = response.getJSONObject("assets");
            assets.setLogo(asset.getString("logo"));
            assets.setLink(asset.getString("link"));
            player.setAssets(assets);
        }
        if (response.has("shapeMargin")) {
            player.setShapeMargin(response.getInt("shapeMargin"));
        }
        if (response.has("shapeRadius")) {
            player.setShapeRadius(response.getInt("shapeRadius"));
        }
        if (response.has("shapeAspect")) {
            player.setShapeAspect(response.getString("shapeAspect"));
        }
        if (response.has("shapeBackgroundTop")) {
            player.setShapeBackgroundTop(response.getString("shapeBackgroundTop"));
        }
        if (response.has("shapeBackgroundBottom")) {
            player.setShapeBackgroundBottom(response.getString("shapeBackgroundBottom"));
        }
        if (response.has("text")) {
            player.setText(response.getString("text"));
        }
        if (response.has("link")) {
            player.setLink(response.getString("link"));
        }
        if (response.has("linkHover")) {
            player.setLinkHover(response.getString("linkHover"));
        }
        if (response.has("linkActive")) {
            player.setLinkActive(response.getString("linkActive"));
        }
        if (response.has("trackPlayed")) {
            player.setTrackPlayed(response.getString("trackPlayed"));
        }
        if (response.has("trackUnplayed")) {
            player.setTrackUnplayed(response.getString("trackUnplayed"));
        }
        if (response.has("trackBackground")) {
            player.setTrackBackground(response.getString("trackBackground"));
        }
        if (response.has("backgroundTop")) {
            player.setBackgroundTop(response.getString("backgroundTop"));
        }
        if (response.has("backgroundBottom")) {
            player.setBackgroundBottom(response.getString("backgroundBottom"));
        }
        if (response.has("backgroundText")) {
            player.setBackgroundText(response.getString("backgroundText"));
        }
        if (response.has("language")) {
            player.setLanguage(response.getString("language"));
        }
        if (response.has("enableApi")) {
            player.setEnableApi(response.getBoolean("enableApi"));
        }
        if (response.has("enableControls")) {
            player.setEnableControls(response.getBoolean("enableControls"));
        }
        if (response.has("forceAutoplay")) {
            player.setForceAutoplay(response.getBoolean("forceAutoplay"));
        }
        if (response.has("hideTitle")) {
            player.setHideTitle(response.getBoolean("hideTitle"));
        }
        if (response.has("forceLoop")) {
            player.setForceLoop(response.getBoolean("forceLoop"));
        }
        return player;
    }

    public List<Player> deserialize(JSONArray data) throws JSONException {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            players.add(deserialize(data.getJSONObject(i)));
        }
        return players;
    }

    public JSONObject serialize(Player player) throws JSONException {
        JSONObject data = new JSONObject();
        if (player.getShapeMargin() != null) {
            data.put("shapeMargin", player.getShapeMargin());
        }
        if (player.getShapeRadius() != null) {
            data.put("shapeRadius", player.getShapeRadius());
        }
        if (player.getShapeAspect() != null) {
            data.put("shapeAspect", player.getShapeAspect());
        }
        if (player.getShapeBackgroundTop() != null && check(player.getShapeBackgroundTop())) {
            data.put("shapeBackgroundTop", player.getShapeBackgroundTop());
        }
        if (player.getShapeBackgroundBottom() != null && check(player.getShapeBackgroundBottom())) {
            data.put("shapeBackgroundBottom", player.getShapeBackgroundBottom());
        }
        if (player.getText() != null && check(player.getText())) {
            data.put("text", player.getText());
        }
        if (player.getLink() != null && check(player.getLink())) {
            data.put("link", player.getLink());
        }
        if (player.getLinkHover() != null && check(player.getLinkHover())) {
            data.put("linkHover", player.getLinkHover());
        }
        if (player.getLinkActive() != null && check(player.getLinkActive())) {
            data.put("linkActive", player.getLinkActive());
        }
        if (player.getTrackPlayed() != null && check(player.getTrackPlayed())) {
            data.put("trackPlayed", player.getTrackPlayed());
        }
        if (player.getTrackUnplayed() != null && check(player.getTrackUnplayed())) {
            data.put("trackUnplayed", player.getTrackUnplayed());
        }
        if (player.getTrackBackground() != null && check(player.getTrackBackground())) {
            data.put("trackBackground", player.getTrackBackground());
        }
        if (player.getBackgroundTop() != null && check(player.getBackgroundTop())) {
            data.put("backgroundTop", player.getBackgroundTop());
        }
        if (player.getBackgroundBottom() != null && check(player.getBackgroundBottom())) {
            data.put("backgroundBottom", player.getBackgroundBottom());
        }
        if (player.getBackgroundText() != null && check(player.getBackgroundText())) {
            data.put("backgroundText", player.getBackgroundText());
        }
        if (player.isEnableApi() != null) {
            data.put("enableApi", player.isEnableApi());
        }
        if (player.isEnableControls() != null) {
            data.put("enableControls", player.isEnableControls());
        }
        if (player.isForceAutoplay() != null) {
            data.put("forceAutoplay", player.isForceAutoplay());
        }
        if (player.isHideTitle() != null) {
            data.put("hideTitle", player.isHideTitle());
        }
        return data;
    }

    private boolean check(String input) {
        Pattern c = Pattern.compile("rgba *\\( *([0-9]+), *([0-9]+), *([0-9]+), ([+-]?([0-9]*[.])?[0-9]+) *\\)");
        Matcher m = c.matcher(input);
        if (m.matches()) {
            if ((Integer.valueOf(m.group(1)) >= 0 && Integer.valueOf(m.group(1)) <= 255) //r
                    && (Integer.valueOf(m.group(2)) >= 0 && Integer.valueOf(m.group(2)) <= 255) //g
                    && (Integer.valueOf(m.group(3)) >= 0 && Integer.valueOf(m.group(3)) <= 255) //b
                    && (Float.valueOf(m.group(4)) >= 0.0 && Float.valueOf(m.group(4)) < 1) //alpha
                    && (m.group(4).length() >= 2 && m.group(4).length() <= 3)) {
                return true;

            } else {
                throw new IllegalArgumentException("Illegal Argument");
            }
        }
        return false;
    }


}
