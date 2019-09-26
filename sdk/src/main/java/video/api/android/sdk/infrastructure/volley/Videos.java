package video.api.android.sdk.infrastructure.volley;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.misc.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import video.api.android.sdk.domain.EmptyResponseListener;
import video.api.android.sdk.domain.ResponseErrorListener;
import video.api.android.sdk.domain.ResponseListener;
import video.api.android.sdk.domain.Status;
import video.api.android.sdk.domain.Video;
import video.api.android.sdk.domain.VideoClient;
import video.api.android.sdk.domain.exceptions.ClientException;
import video.api.android.sdk.domain.exceptions.ServerException;
import video.api.android.sdk.domain.pagination.PageLoaderVideos;
import video.api.android.sdk.domain.pagination.PageResponseListener;
import video.api.android.sdk.domain.pagination.Pager;
import video.api.android.sdk.domain.pagination.PagerBuilder;
import video.api.android.sdk.domain.pagination.VideoFilter;
import video.api.android.sdk.infrastructure.JsonSerializer;
import video.api.android.sdk.infrastructure.PageJsonSerializer;
import video.api.android.sdk.infrastructure.StatusJsonSerializer;
import video.api.android.sdk.infrastructure.volley.request.RequestBuilder;

public class Videos implements VideoClient, PageLoaderVideos<Video> {
    private RequestExecutor           requestExecutor;
    private JsonSerializer<Video>     videoJsonSerializer;
    private StatusJsonSerializer      statusJsonSerializer;
    private PageJsonSerializer<Video> videoPageJsonSerializer;

    public Videos(RequestExecutor requestExecutor, JsonSerializer<Video> videoJsonSerializer, StatusJsonSerializer statusJsonSerializer, PageJsonSerializer<Video> videoPageJsonSerializer) {
        this.videoJsonSerializer = videoJsonSerializer;
        this.statusJsonSerializer = statusJsonSerializer;
        this.requestExecutor = requestExecutor;
        this.videoPageJsonSerializer = videoPageJsonSerializer;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void create(Video video, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener) throws JSONException {
        RequestBuilder requestBuilder = new RequestBuilder()
                .jsonRequest(videoJsonSerializer.serialize(video), response -> {
                    try {
                        videoResponseListener.onResponse(videoJsonSerializer.deserialize(response));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.POST)
                .setUrl("/videos")
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void update(Video video, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener) throws JSONException {
        RequestBuilder requestBuilder = new RequestBuilder()
                .jsonRequest(videoJsonSerializer.serialize(video), response -> {
                    try {
                        videoResponseListener.onResponse(videoJsonSerializer.deserialize(response));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.PATCH)
                .setUrl("/videos/" + video.getVideoId())
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public void uploadThumbnail(String videoId, String thumbnailPath, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .mutipartRequest(thumbnailPath, response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        videoResponseListener.onResponse(videoJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.POST)
                .setUrl("/videos/" + videoId + "/thumbnail")
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public void updateThumbnailWithTimeCode(String videoId, String timecode, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener) {
        Map<String, String> jsonParams = new HashMap<>();
        jsonParams.put("timecode", timecode);
        RequestBuilder requestBuilder = new RequestBuilder()
                .jsonRequest(new JSONObject(jsonParams), response -> {
                    try {
                        videoResponseListener.onResponse(videoJsonSerializer.deserialize(response));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.PATCH)
                .setUrl("/videos/" + videoId + "/thumbnail")
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    private void uploadSmallVideo(Video video, String videoPath, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .mutipartRequest(videoPath, response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        videoResponseListener.onResponse(videoJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.POST)
                .setUrl("/videos/" + video.getVideoId() + "/source")
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    private void uploadBigVideo(Video video, String videoPath, String contentRange, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .mutipartRequest(videoPath, response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        File file = new File(videoPath);
                        file.deleteOnExit();
                        if (response.contains("title")) {
                            videoResponseListener.onResponse(videoJsonSerializer.deserialize(jsonObject));
                        }
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .addHeader("Content-Range", contentRange)
                .setMethod(Request.Method.POST)
                .setUrl("/videos/" + video.getVideoId() + "/source")
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    private static ArrayList<String> split(String filePath, long splitlen) {
        long leng = 0;
        int count = 1, data;
        try {
            File filename = new File(filePath);
            InputStream infile = new BufferedInputStream(new FileInputStream(filename));
            data = infile.read();
            ArrayList<String> filesPath = new ArrayList<>();
            while (data != -1) {
                //create the new files
                String name = filePath.substring(0, filePath.lastIndexOf('.') - 1) + count;
                filename = new File(name);
                filesPath.add(name);
                OutputStream outfile = new BufferedOutputStream(new FileOutputStream(filename));
                //write the new files
                while (data != -1 && leng < splitlen) {
                    outfile.write(data);
                    leng++;
                    data = infile.read();
                }
                leng = 0;
                outfile.close();
                count++;
            }

            return filesPath;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void uploadVideo(Video video, String videoPath, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener) {
        int ChunkSize = 79 * 1024 * 1024;
        File fileToUpload = new File(videoPath);
        if (!fileToUpload.exists() || !fileToUpload.isFile()) {
            System.exit(1);
        }
        int size = (int) fileToUpload.length();
        // Upload in a single request when file is small enough
        if (size <= 0) {
            throw new Error(videoPath + " is empty");
        }
        //uploadVideo video (less than 80 MB)
        if (size < ChunkSize) {
            uploadSmallVideo(video, videoPath, videoResponseListener, responseErrorListener);
        } else {
            ArrayList<String> filesName = split(videoPath, ChunkSize);
            int copiedBytes = 0;
            for (int i = 0; i < filesName.size(); i++) {
                File chunkFile = new File(filesName.get(i));
                int from = copiedBytes;
                copiedBytes += chunkFile.length();
                String contentRange = "bytes " + from + "-" + (copiedBytes - 1) + "/" + size;
                uploadBigVideo(video, filesName.get(i), contentRange, videoResponseListener, responseErrorListener);
            }
        }
    }

    public void upload(Video video, String videoPath, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener) {
        class UploadVideo extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected String doInBackground(Void... params) {
                if (video.getVideoId() == null) {
                    if (video.getTitle() == null) {
                        video.setTitle(videoPath.substring(videoPath.lastIndexOf('/') + 1, videoPath.lastIndexOf('.')));
                    }
                    try {
                        create(video, vid -> {
                            video.setVideoId(vid.getVideoId());
                            uploadVideo(video, videoPath, videoResponseListener, responseErrorListener);
                        }, responseErrorListener);
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(e);
                    }
                } else {
                    uploadVideo(video, videoPath, videoResponseListener, responseErrorListener);
                }
                return null;
            }
        }
        UploadVideo uv = new UploadVideo();
        uv.execute();
    }

    public void upload(String videoPath, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener) {
        upload(new Video(), videoPath, videoResponseListener, responseErrorListener);
    }

    public void get(String videoId, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        videoResponseListener.onResponse(videoJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.GET)
                .setUrl("/videos/" + videoId)
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public void getStatus(String videoId, ResponseListener<Status> statusResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        statusResponseListener.onResponse(statusJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.GET)
                .setUrl("/videos/" + videoId + "/status")
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public void delete(String videoId, EmptyResponseListener emptyResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> emptyResponseListener.onResponse())
                .setMethod(Request.Method.DELETE)
                .setUrl("/videos/" + videoId)
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }

    public Pager<Video> search(PagerBuilder pagerBuilder, VideoFilter videoFilter) {
        return new Pager<>(this, pagerBuilder, videoFilter);
    }

    public Pager<Video> search(VideoFilter videoFilter) {
        return search(new PagerBuilder(), videoFilter);
    }

    public Pager<Video> list(PagerBuilder pagerBuilder) {
        return search(pagerBuilder, new VideoFilter());
    }

    public Pager<Video> list() {
        return list(new PagerBuilder());
    }

    public void loadPage(int page, PagerBuilder pagerBuilder, VideoFilter videoFilter, PageResponseListener<Video> listener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .stringRequest(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        listener.onResponse(videoPageJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.GET)
                .setUrl("/videos?currentPage=" + page + pagerBuilder.build() + videoFilter.build())
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);

    }

    public void delegatedUpload(String token, String videoPath, ResponseListener<Video> videoResponseListener, ResponseErrorListener responseErrorListener) {
        RequestBuilder requestBuilder = new RequestBuilder()
                .mutipartRequest(videoPath, response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        videoResponseListener.onResponse(videoJsonSerializer.deserialize(jsonObject));
                    } catch (JSONException e) {
                        responseErrorListener.onErrorResponse(new ServerException("could not parse response ", e));
                    }
                })
                .setMethod(Request.Method.POST)
                .setUrl("/uploadVideo?token=" + token)
                .setErrorListener(error -> {
                    if (error.networkResponse.statusCode >= 500) {
                        responseErrorListener.onErrorResponse(new ServerException("ServerException " + error.networkResponse.statusCode, error));
                    } else if (error.networkResponse.statusCode >= 400) {
                        responseErrorListener.onErrorResponse(new ClientException("ClientException " + error.networkResponse.statusCode, error));
                    }
                });
        requestExecutor.execute(requestBuilder);
    }


}
