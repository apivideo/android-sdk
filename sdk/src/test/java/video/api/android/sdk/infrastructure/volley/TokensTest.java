package video.api.android.sdk.infrastructure.volley;

import com.android.volley.Request;

import org.junit.Before;
import org.junit.Test;

import video.api.android.sdk.domain.TokenClient;
import video.api.android.sdk.infrastructure.TokenJsonSerializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TokensTest {
    private       TokenClient         tokenClient;
    private final TestRequestExecutor requestExecutor = new TestRequestExecutor();

    @Before
    public void setUp() {
        tokenClient = new Tokens(requestExecutor, new TokenJsonSerializer());
    }

    @Test
    public void generate() {
        tokenClient.generate(null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/tokens"));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.POST);
    }
}