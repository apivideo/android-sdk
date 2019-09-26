package video.api.android.sdk.domain.pagination;

public class PagerBuilder {
    private Integer pageSize;
    private String  sortBy;
    private String  sortOrder;


    public PagerBuilder withPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public PagerBuilder withSortBy(String sortBy) {
        this.sortBy = sortBy;
        return this;
    }

    public PagerBuilder withSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public String build() {
        StringBuilder url = new StringBuilder();
        if (pageSize != null) {
            url.append("&pageSize=").append(pageSize);
        }
        if (sortBy != null) {
            url.append("&sortBy=").append(sortBy);
        }
        if (sortOrder != null) {
            url.append("&sortOrder=").append(sortOrder);
        }
        return url.toString();
    }
}
