package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import video.api.android.sdk.domain.Account;

import static org.junit.Assert.assertEquals;

public class AccountJsonSerializerTest {

    private JsonSerializer<Account> accountJsonSerializer;

    @Before
    public void setUp() {
        accountJsonSerializer = new AccountJsonSerializer();
    }

    @Test(expected = JSONException.class)
    public void deserializeFailure() throws JSONException {
        accountJsonSerializer.deserialize(new JSONObject());
    }

    @Test(expected = RuntimeException.class)
    public void deserializeAllFailure() throws JSONException {
        accountJsonSerializer.deserialize(new JSONArray());
    }

    @Test(expected = RuntimeException.class)
    public void serializeFailure() throws JSONException {
        accountJsonSerializer.serialize(new Account());
    }

    @Test
    public void deserializeSuccess() throws JSONException {
        Map<Object, Object> jsonParams = new HashMap<>();
        Map<Object, Object> quotaParams = new HashMap<>();
        Map<Object, Object> termParams = new HashMap<>();
        quotaParams.put("quotaUsed", 0);
        quotaParams.put("quotaRemaining", 3000);
        quotaParams.put("quotaTotal", 3000);
        termParams.put("startAt", "2019-09-05");
        termParams.put("endAt", "2019-09-05");
        jsonParams.put("quota", new JSONObject(quotaParams));
        jsonParams.put("term", new JSONObject(termParams));
        Account account = accountJsonSerializer.deserialize(new JSONObject(jsonParams));
        assertEquals(0, account.getQuota().getQuotaUsed());
        assertEquals(3000, account.getQuota().getQuotaRemaining());
        assertEquals(3000, account.getQuota().getQuotaTotal());
        assertEquals("2019-09-05", account.getTerm().getStartAt());
        assertEquals("2019-09-05", account.getTerm().getEndAt());
    }

}