package video.api.android.sdk.domain.pagination;

import java.util.List;

public class Page<T> {
    private final int currentPage;
    private final int pagesTotal;
    private final int itemsTotal;
    private final List<T> items;

    public Page(int currentPage, int pagesTotal, int itemsTotal, List<T> items) {
        this.currentPage = currentPage;
        this.pagesTotal = pagesTotal;
        this.itemsTotal = itemsTotal;
        this.items = items;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPagesTotal() {
        return pagesTotal;
    }

    public int getItemsTotal() {
        return itemsTotal;
    }

    public List<T> getItems() {
        return items;
    }
}
