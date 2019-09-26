package video.api.android.sdk.infrastructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import video.api.android.sdk.domain.Account;
import video.api.android.sdk.domain.Quota;
import video.api.android.sdk.domain.Term;

public class AccountJsonSerializer implements JsonSerializer<Account> {

    public Account deserialize(JSONObject response) throws JSONException {
        Account account = new Account();
        Quota quota = new Quota();
        Term term = new Term();
        int quotaUsed = response.getJSONObject("quota").getInt("quotaUsed");
        int quotaRemaining = response.getJSONObject("quota").getInt("quotaRemaining");
        int quotaTotal = response.getJSONObject("quota").getInt("quotaTotal");
        String startAt = response.getJSONObject("term").getString("startAt");
        String endAt = response.getJSONObject("term").getString("endAt");
        quota.setQuotaUsed(quotaUsed);
        quota.setQuotaRemaining(quotaRemaining);
        quota.setQuotaTotal(quotaTotal);
        term.setStartAt(startAt);
        term.setEndAt(endAt);
        account.setQuota(quota);
        account.setTerm(term);
        return account;
    }

    public List<Account> deserialize(JSONArray data) {
        throw new RuntimeException("Not supported");
    }

    public JSONObject serialize(Account object) {
        throw new RuntimeException("Not supported");
    }

}
