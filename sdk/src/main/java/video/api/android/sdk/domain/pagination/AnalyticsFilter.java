package video.api.android.sdk.domain.pagination;

import java.util.ArrayList;
import java.util.Map;

public class AnalyticsFilter {
    private ArrayList<String>   tags;
    private Map<String, String> metadata;

    public AnalyticsFilter withTags(ArrayList<String> tags) {
        this.tags = tags;
        return this;
    }

    public AnalyticsFilter withMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }

    public String build() {
        StringBuilder url = new StringBuilder();
        if (tags != null) {
            for (int i = 0; i < tags.size(); i++) {
                url.append("&tags[").append(i).append("]=").append(tags.get(i));
            }
        }
        if (metadata != null) {
            for (Map.Entry<String, String> e : metadata.entrySet()) {
                url.append("&metadata[").append(e.getKey()).append("]=").append(e.getValue());
            }
        }
        return url.toString();
    }
}
