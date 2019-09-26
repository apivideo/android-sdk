package video.api.android.sdk.infrastructure.volley;

import com.android.volley.Request;

import org.junit.Before;
import org.junit.Test;

import video.api.android.sdk.domain.AccountClient;
import video.api.android.sdk.infrastructure.AccountJsonSerializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountsTest {
    private       AccountClient       accountClient;
    private final TestRequestExecutor requestExecutor = new TestRequestExecutor();

    @Before
    public void setUp() {
        accountClient = new Accounts(requestExecutor, new AccountJsonSerializer());
    }

    @Test
    public void get() {
        accountClient.get(null, null);
        assertTrue(requestExecutor.getRequestBuilder().getUrl().endsWith("/account"));
        assertEquals(requestExecutor.getRequestBuilder().getMethod(), Request.Method.GET);
    }
}