package video.api.android.sdk.domain;

public class Player {
    private String       playerId;
    private Integer      shapeMargin;
    private Integer      shapeRadius;
    private String       shapeAspect;
    private String       shapeBackgroundTop;
    private String       shapeBackgroundBottom;
    private String       text;
    private String       link;
    private String       linkHover;
    private String       linkActive;
    private String       trackPlayed;
    private String       trackUnplayed;
    private String       trackBackground;
    private String       backgroundTop;
    private String       backgroundBottom;
    private String       backgroundText;
    private String       language;
    private Boolean      enableApi;
    private Boolean      enableControls;
    private Boolean      forceAutoplay;
    private Boolean      hideTitle;
    private Boolean      forceLoop;
    private AssetsPlayer assets;
    public  boolean      c;

    public Player() {
    }

    public Player(String playerId, Integer shapeMargin, Integer shapeRadius, String shapeAspect, String shapeBackgroundTop, String shapeBackgroundBottom, String text, String link, String linkHover, String linkActive, String trackPlayed, String trackUnplayed, String trackBackground, String backgroundTop, String backgroundBottom, String backgroundText, String language, Boolean enableApi, Boolean enableControls, Boolean forceAutoplay, Boolean hideTitle, Boolean forceLoop, AssetsPlayer assets) {
        this.playerId = playerId;
        this.shapeMargin = shapeMargin;
        this.shapeRadius = shapeRadius;
        this.shapeAspect = shapeAspect;
        this.shapeBackgroundTop = shapeBackgroundTop;
        this.shapeBackgroundBottom = shapeBackgroundBottom;
        this.text = text;
        this.link = link;
        this.linkHover = linkHover;
        this.linkActive = linkActive;
        this.trackPlayed = trackPlayed;
        this.trackUnplayed = trackUnplayed;
        this.trackBackground = trackBackground;
        this.backgroundTop = backgroundTop;
        this.backgroundBottom = backgroundBottom;
        this.backgroundText = backgroundText;
        this.language = language;
        this.enableApi = enableApi;
        this.enableControls = enableControls;
        this.forceAutoplay = forceAutoplay;
        this.hideTitle = hideTitle;
        this.forceLoop = forceLoop;
        this.assets = assets;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Integer getShapeMargin() {
        return shapeMargin;
    }

    public void setShapeMargin(Integer shapeMargin) {
        this.shapeMargin = shapeMargin;
    }

    public Integer getShapeRadius() {
        return shapeRadius;
    }

    public void setShapeRadius(Integer shapeRadius) {
        this.shapeRadius = shapeRadius;
    }

    public String getShapeAspect() {
        return shapeAspect;
    }

    public void setShapeAspect(String shapeAspect) {
        this.shapeAspect = shapeAspect;
    }

    public String getShapeBackgroundTop() {
        return shapeBackgroundTop;
    }

    public void setShapeBackgroundTop(String shapeBackgroundTop) {
        this.shapeBackgroundTop = shapeBackgroundTop;
    }

    public String getShapeBackgroundBottom() {
        return shapeBackgroundBottom;
    }

    public void setShapeBackgroundBottom(String shapeBackgroundBottom) {
        this.shapeBackgroundBottom = shapeBackgroundBottom;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkHover() {
        return linkHover;
    }

    public void setLinkHover(String linkHover) {
        this.linkHover = linkHover;
    }

    public String getLinkActive() {
        return linkActive;
    }

    public void setLinkActive(String linkActive) {
        this.linkActive = linkActive;
    }

    public String getTrackPlayed() {
        return trackPlayed;
    }

    public void setTrackPlayed(String trackPlayed) {
        this.trackPlayed = trackPlayed;
    }

    public String getTrackUnplayed() {
        return trackUnplayed;
    }

    public void setTrackUnplayed(String trackUnplayed) {
        this.trackUnplayed = trackUnplayed;
    }

    public String getTrackBackground() {
        return trackBackground;
    }

    public void setTrackBackground(String trackBackground) {
        this.trackBackground = trackBackground;
    }

    public String getBackgroundTop() {
        return backgroundTop;
    }

    public void setBackgroundTop(String backgroundTop) {
        this.backgroundTop = backgroundTop;
    }

    public String getBackgroundBottom() {
        return backgroundBottom;
    }

    public void setBackgroundBottom(String backgroundBottom) {
        this.backgroundBottom = backgroundBottom;
    }

    public String getBackgroundText() {
        return backgroundText;
    }

    public void setBackgroundText(String backgroundText) {
        this.backgroundText = backgroundText;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean isEnableApi() {
        return enableApi;
    }

    public void setEnableApi(Boolean enableApi) {
        this.enableApi = enableApi;
    }

    public Boolean isEnableControls() {
        return enableControls;
    }

    public void setEnableControls(Boolean enableControls) {
        this.enableControls = enableControls;
    }

    public Boolean isForceAutoplay() {
        return forceAutoplay;
    }

    public void setForceAutoplay(Boolean forceAutoplay) {
        this.forceAutoplay = forceAutoplay;
    }

    public Boolean isHideTitle() {
        return hideTitle;
    }

    public void setHideTitle(Boolean hideTitle) {
        this.hideTitle = hideTitle;
    }

    public Boolean isForceLoop() {
        return forceLoop;
    }

    public void setForceLoop(Boolean forceLoop) {
        this.forceLoop = forceLoop;
    }

    public AssetsPlayer getAssets() {
        return assets;
    }

    public void setAssets(AssetsPlayer assets) {
        this.assets = assets;
    }
}
