# api.video ANDROID SDK
The [api.video](https://api.video/) web-service helps you put video on the web without the hassle. 
This documentation helps you use the corresponding ANDROID client.
This is an early version, feel free to report any issue.

## Install

### With Gradle

1. Download the [latest release]().
2. Add the following dependencies in your `build.gradle` file:

```gradle
// build.gradle
dependencies {

}
``` 

### Quick start
```java 
// Create client for Production and authenticate
Client client = new ClientFactory().create(context, apiKey.getText().toString())

// Create client for Sandbox and authenticate
Client client = new ClientFactory().createSandbox(context, apiKey.getText().toString())

// Create and upload a video resource from local drive
client.videos.upload("/path/to/video.mp4", new ResponseListener<Video>() {
    @Override
    public void onResponse(Video video) {
        // Get its embed code
        video.getAssets().getIframe();// <iframe src="..."></iframe>
    }
}, new ResponseErrorListener() {
    @Override
    public void onErrorResponse(Exception error) {
        Log.d("error",error.getMessage());
    }
});
```



### Full API
```java 
/*
 *********************************
 *********************************
 *         VIDEOS                *
 *********************************
 *********************************
*/


// Create a video
Video video = new Video();
video.setTitle(title);
try {
    client.videos.create(video, video -> Log.d("videoId", video.getVideoId()), error -> Log.d("error",error.getMessage()));
} catch (JSONException e) {
    Log.d("error",e.printStackTrace());
}

// Create and upload a video resource from local drive
client.videos.upload("/path/to/video.mp4", video -> {
    // Get its embed code
    video.getAssets().getIframe();// <iframe src="..."></iframe>
}, error -> Log.d("error",error.getMessage()));

//upload a video resource from local drive
Video video = new Video();
video.setVideoId(videoId);
client.videos.upload(video, "/path/to/video.mp4", video -> {
    // Get its embed code
    video.getAssets().getIframe();// <iframe src="..."></iframe>
}, error -> Log.d("error",error.getMessage()));

// Show a video
client.videos.get(videoId, (ResponseListener<Video>) video -> Log.d("title", video.getTitle()),
        (ResponseErrorListener) error -> Log.d("error",error.getMessage()));

// Show video status
client.videos.getStatus(videoId, (ResponseListener<Status>) status -> Log.d("status", status.getIngest().getStatus()),
        (ResponseErrorListener) error -> Log.d("error", error.getMessage()));

// Update video properties
Video video = new Video();
video.setVideoId(videoId);
video.setTitle(title);
try {
    client.videos.update(video, video -> Log.d("title", video.getTitle()), 
            error -> Log.d("error",error.getMessage()));
} catch (JSONException e) {
    Log.d("error",e.printStackTrace());
}

// Delete video (file and data)
client.videos.delete(videoId, (EmptyResponseListener) () -> {
}, (ResponseErrorListener) error -> Log.d("error",error.getMessage()));

// Upload a thumbnail for video
client.videos.uploadThumbnail(videoId,"/path/to/thumbnail", (ResponseListener<Video>) video -> {
}, (ResponseErrorListener) error -> Log.d("error", error.getMessage()));

// Update video's thumbnail by picking timecode
client.videos.updateThumbnailWithTimeCode(videoId,timeCode, (ResponseListener<Video>) video -> {
}, (ResponseErrorListener) error -> Log.d("error", error.getMessage()));
            
// List videos with Pagination
PagerBuilder pagerBuilder = new PagerBuilder();
pagerBuilder.withPageSize(pageSize);
pagerBuilder.withSortBy(sortBy);
pagerBuilder.withSortOrder(sortOrder);
Pager<Video> pager = client.videos.list(pagerBuilder);
onScroll();
//when we scroll we call onScroll()


 void onScroll() {
    pager.next(page -> {
        if (page != null) {
            //add item to listView for example
        }
    }, error -> Log.d("error", error.getMessage());
}

// List videos 
Pager<Video> pager = client.videos.list();

// Search videos with Pagination
VideoFilter  videoFilter  = new VideoFilter();
PagerBuilder pagerBuilder = new PagerBuilder();
pagerBuilder.withPageSize(pageSize);
pagerBuilder.withSortBy(sortBy);
pagerBuilder.withSortOrder(sortOrder);
videoFilter.byTitle("title");
Pager<Video> client.videos.search(pagerBuilder, videoFilter);
onScroll();
//when we scroll we call onScroll()


 void onScroll() {
    pager.next(page -> {
        if (page != null) {
            //add item to listView for example
        }
    }, error -> Log.d("error", error.getMessage());
}

// Search videos
Pager<Video> client.videos.search(videoFilter);

// Delegated upload
//generate a token for someone to upload a video into your account
client.tokens.generate(token -> {
    // ...then upload from anywhere without authentication:
    client.videos.delegatedUpload(token.getToken(), "/path/to/video.mp4", video -> {
    }, error -> Log.d("error", error.getMessage()));
}, error -> Log.d("error", error.getMessage()));


/*
 *********************************
 *         CAPTIONS              *
 *********************************
*/


// Get caption for a video
client.captions.get(videoId, language, (ResponseListener<Caption>) caption -> Log.d("uri", caption.getUri()),
        (ResponseErrorListener) error -> Log.d("error",error.getMessage()));

// Get all captions for a video
client.captions.getAll(videoId, captions -> {
    //add to ListView for example
}, error -> Log.d("error",error.getMessage()));

// Upload a caption file for a video (.vtt)
EditText fileContent;
try {
    file = new File(Environment.getExternalStorageDirectory(), "caption.vtt");
    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
    writer.write(fileContent.getText().toString());
    writer.newLine();
    writer.flush();
    writer.close();
} catch (IOException e) {
    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
}
client.captions.upload(videoId, language, file.getPath(), caption -> Log.d("Caption uploaded","Caption uploaded"),
        error -> Log.d("error", error.getMessage()));

// Set default caption for a video
Caption caption = new Caption();
caption.setDefaut(true);
client.captions.update(videoId, language, caption, (ResponseListener<Caption>) caption1 -> Log.d("Caption updated","Caption updated"),
        (ResponseErrorListener) error -> Log.d("error", error.getMessage()));

// Delete video's caption
client.captions.delete(videoId, language, (EmptyResponseListener) () -> {
}, (ResponseErrorListener) error -> Log.d("error", error.getMessage()));


/*
 *********************************
 *         PLAYERS               *
 *********************************
*/


// Get a player
client.players.get(playerId, player -> Log.d("EnableApi", player.isEnableApi()),
        error -> Log.d("error",error.getMessage()));

// Create a player
client.players.create(new Player(), player -> Log.d("PlayerId", player.getPlayerId()),
        error -> Log.d("error",error.getMessage()));

// Update player's properties
Player player = new Player();
player.setPlayerId(playerId);
player.setShapeMargin(playerShapeMargin);
try {
    client.players.update(player, player ->  Log.d("Player updated", "Player updated"),
            error -> Log.d("error",error.getMessage()));
} catch (JSONException e) {
    e.printStackTrace();
}

// Delete a player
client.players.delete(playerId, () -> {
},error -> Log.d("error",error.getMessage()));


// Upload a logo
client.players.uploadLogo(playerId, logoPath, playerLink, player -> Log.d("Logo uploaded", "Logo uploaded"),
        error -> Log.d("error", error.getMessage()));


// List players with Pagination
Pager<Player> pager = client.players.list(pageSize);
onScroll();
//when we scroll we call onScroll()


void onScroll() {
    pager.next(page -> {
        if (page != null) {
            //add item to listView for example
        }
    }, error -> Log.d("error", error.getMessage());
}

// List players 
Pager<Player> pager = client.players.list();


/*
 *********************************
 *********************************
 *         LIVESTREAMS                 *
 *********************************
 *********************************
*/

// Show a livestream
client.liveStreams.get(liveStreamId, liveStream -> Log.d("liveName",liveStream.getName()),
        error -> Log.d("error", error.getMessage()));
        
// Create livestream properties
try {
    client.liveStreams.create(new LiveStream(), liveStream -> Log.d("LiveStreamId", liveStream.getLiveStreamId()),
            error -> Log.d("error", error.getMessage()));
} catch (JSONException e) {
    e.printStackTrace();
}

// Update livestream properties
Live liveStream = new LiveStream();
liveStream.setLiveStreamId(liveStreamId);
liveStream.setName(name);
try {
    client.liveStreams.update(liveStream, liveStream -> Log.d("Live Updated", "Live Updated"),
            error -> Log.d("error", error.getMessage()));
} catch (JSONException e) {
    e.printStackTrace();
}

// Delete livestream (file and data)
client.liveStreams.delete(liveStreamId, () -> {
}, error -> Log.d("error", error.getMessage()));
        
// Upload a thumbnail for livestream
client.liveStreams.uploadThumbnail(liveStreamId, thumbnailPath, (ResponseListener<LiveStream>) liveStream -> Log.d("Thumbnail uploaded", "Thumbnail uploaded"),
        (ResponseErrorListener) error -> Log.d("error", error.getMessage()));
        
// Delete livestream's thumbnail
client.liveStreams.deleteThumbnail(liveStreamId, liveStream -> {
    }, error -> Log.d("error", error.getMessage()));

// List livestream with Pagination
 Pager<LiveStream> pager = client.liveStreams.list(pageSize);
onScroll();
//when we scroll we call onScroll()


void onScroll() {
    pager.next(page -> {
        if (page != null) {
            //add item to listView for example
        }
    }, error -> Log.d("error", error.getMessage());
}
// List livestream 
 Pager<LiveStream> pager = client.liveStreams.list();

/*
 *********************************
 *********************************
 *         ANALYTICS             *
 *********************************
 *********************************
*/

// Get video analytics between period
client.videoAnalytics.getVideoAnalytics(videoId, period, (ResponseListener<AnalyticVideo>) analyticVideo -> {
    //add to ListView for example
}, (ResponseErrorListener) error -> Log.d("error", error.getMessage()));

// list videos analytics between period with Pagination
PagerBuilder pagerBuilder = new PagerBuilder();
pagerBuilder.withPageSize(pageSize);
pagerBuilder.withSortBy(sortBy);
pagerBuilder.withSortOrder(sortOrder);
Pager<AnalyticVideo> pager = client.videoAnalytics.list(period, pagerBuilder);
onScroll();
//when we scroll we call onScroll()


void onScroll () {
    pager.next(page -> {
        if (page != null) {
            //add item to listView for example
        }
    }, error -> Log.d("error", error.getMessage());
}

// list videos analytics between period
Pager<AnalyticVideo> pager = client.videoAnalytics.list(period);

// Search videos analytics between period with Pagination, filter with tags or metadata 
AnalyticsFinder analyticsFilter = new AnalyticsFinder();
PagerBuilder pagerBuilder = new PagerBuilder();
pagerBuilder.withPageSize(pageSize);
pagerBuilder.withSortBy(sortBy);
pagerBuilder.withSortOrder(sortOrder);
analyticsFilter.byMetadata(metadata);
Pager<AnalyticVideo> pager = client.videoAnalytics.search(period, pagerBuilder, analyticsFilter);
onScroll();
//when we scroll we call onScroll()


void onScroll () {
    pager.next(page -> {
        if (page != null) {
            //add item to listView for example
        }
    }, error -> Log.d("error", error.getMessage());
}

// Search videos analytics between period, filter with tags or metadata 
Pager<AnalyticVideo> pager = client.videoAnalytics.search(period, analyticsFilter);


// Get liveStream analytics between period
client.liveStreamAnalytics.getLiveAnalytics(liveStreamId, period, (ResponseListener<AnalyticLive>) analyticLive -> {
    //add to ListView for example
}, (ResponseErrorListener) error -> Log.d("error", error.getMessage()));

// list lives analytics between period with Pagination
PagerBuilder pagerBuilder = new PagerBuilder();
pagerBuilder.withPageSize(pageSize);
pagerBuilder.withSortBy(sortBy);
pagerBuilder.withSortOrder(sortOrder);
Pager<AnalyticLive> pager = client.liveStreamAnalytics.list(period, pagerBuilder);
onScroll();
//when we scroll we call onScroll()


void onScroll () {
    pager.next(page -> {
        if (page != null) {
            //add item to listView for example
        }
    }, error -> Log.d("error", error.getMessage());
}

// list lives analytics between period 
Pager<AnalyticLive> pager = client.liveStreamAnalytics.list(period);

// Search lives analytics between period with Pagination, filter with tags or metadata
AnalyticsFinder analyticsFilter = new AnalyticsFinder();
PagerBuilder pagerBuilder = new PagerBuilder();
pagerBuilder.withPageSize(pageSize);
pagerBuilder.withSortBy(sortBy);
pagerBuilder.withSortOrder(sortOrder);
analyticsFilter.byMetadata(metadata);
 Pager<AnalyticLive> pager = client.liveStreamAnalytics.search(period, pagerBuilder, analyticsFilter);
onScroll();
//when we scroll we call onScroll()


void onScroll () {
    pager.next(page -> {
        if (page != null) {
            //add item to listView for example
        }
    }, error -> Log.d("error", error.getMessage());
}

// Search lives analytics between period, filter with tags or metadata
 Pager<AnalyticLive> pager = client.liveStreamAnalytics.search(period, analyticsFilter);


// list analytics session events with Pagination
Pager<AnalyticEvent> pager = client.sessionEventAnalytics.list(sessionId, pageSize);
onScroll();
//when we scroll we call onScroll()
 
 
void onScroll () {
     pager.next(page -> {
         if (page != null) {
             //add item to listView for example
         }
     }, error -> Log.d("error", error.getMessage());
 }

// list analytics session events
Pager<AnalyticEvent> pager = client.sessionEventAnalytics.list(sessionId);


/*
 *********************************
 *********************************
 *         ACCOUNT               *
 *********************************
 *********************************
*/

// Show an Account
client.accounts.get(account -> Log.d("quotaUsed", String.valueOf(account.getQuota().getQuotaUsed())),
        error -> Log.d("error", error.getMessage()));

/*
 *********************************
 *********************************
 *         TOKENS                *
 *********************************
 *********************************
*/

// Generate Token
client.tokens.generate(token -> Log.d("token",token.getToken()),
        error -> Log.d("error", error.getMessage()));

```


### API coverage
Most of _api.video_ features are implemented and autocomplete friendly within your favorite IDE:

```java 
client.videos; // https://docs.api.video/5.1/videos
client.liveStreams; // https://docs.api.video/5.1/liveStream
client.captions; // https://docs.api.video/5.1/captions
client.players; // https://docs.api.video/5.1/players
client.account; // https://docs.api.video/5.1/account
client.tokens; // https://docs.api.video/5.1/tokens 

// https://docs.api.video/5.1/analytics
client.videoAnalytics;
client.liveStreamAnalytics; 
client.sessionEventAnalytics; 

```

