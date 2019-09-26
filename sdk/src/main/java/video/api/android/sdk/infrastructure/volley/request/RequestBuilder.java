package video.api.android.sdk.infrastructure.volley.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.request.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import video.api.android.sdk.domain.exceptions.ClientException;

public class RequestBuilder {
    private       int                    method;
    private       String                 url;
    private       Response.ErrorListener errorListener;
    private final Map<String, String>    headers = new HashMap<>();
    private       Callable<Request>      constructor;


    public RequestBuilder stringRequest(final Response.Listener<String> listener) {
        constructor = () -> new StringRequest(method, url, listener, errorListener) {
            @Override
            public Map<String, String> getHeaders() {
                return headers;
            }
        };

        return this;
    }

    public RequestBuilder jsonRequest(final JSONObject body, final Response.Listener<JSONObject> listener) {
        constructor = () -> new JsonObjectRequest(method, url, body, listener, errorListener) {
            @Override
            public Map<String, String> getHeaders() {
                return headers;
            }
        };

        return this;
    }

    public RequestBuilder mutipartRequest(final String filePath, final Response.Listener<String> listener) {
        constructor = () -> {
            SimpleMultiPartRequest request = new SimpleMultiPartRequest(method, url, listener, errorListener) {
                @Override
                public Map<String, String> getHeaders() {
                    return headers;
                }
            };
            request.addFile("file", filePath);
            return request;
        };

        return this;
    }

    public RequestBuilder mutipartRequest(final String filePath, final String link, final Response.Listener<String> listener) {
        constructor = () -> {
            SimpleMultiPartRequest request = new SimpleMultiPartRequest(method, url, listener, errorListener) {
                @Override
                public Map<String, String> getHeaders() {
                    return headers;
                }
            };
            request.addFile("file", filePath);
            request.addStringParam("link", link);

            return request;
        };

        return this;
    }

    public RequestBuilder setMethod(int method) {
        this.method = method;

        return this;
    }

    public int getMethod() {
        return method;
    }

    public RequestBuilder setUrl(String url) {
        this.url = url;

        return this;
    }

    public String getUrl() {
        return url;
    }

    public RequestBuilder setErrorListener(Response.ErrorListener errorListener) {
        this.errorListener = errorListener;
        return this;
    }

    public Response.ErrorListener getErrorListener() {
        return errorListener;
    }

    public RequestBuilder addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public Request build() throws ClientException {
        try {
            return constructor.call();
        } catch (Exception e) {
            throw new ClientException("can't build request", e);
        }
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Callable<Request> getConstructor() {
        return constructor;
    }
}

