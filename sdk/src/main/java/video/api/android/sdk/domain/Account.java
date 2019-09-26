package video.api.android.sdk.domain;

public class Account {

    private Quota quota;
    private Term term;

    public Account(Quota quota, Term term) {
        this.quota = quota;
        this.term = term;
    }

    public Account() {
    }

    public Quota getQuota() {
        return quota;
    }

    public void setQuota(Quota quota) {
        this.quota = quota;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }
}
