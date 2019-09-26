package video.api.android.sdk.domain.pagination;

import video.api.android.sdk.domain.ResponseErrorListener;

public class Pager<T> {
    private PageLoader<T>         pageLoader;
    private PageLoaderVideos<T>   pageLoaderVideos;
    private PageLoaderAnalytic<T> pageLoaderAnalytic;
    private int                   currentPageNum = 1;
    private boolean               hasNext        = true;
    private String                period;
    private PagerBuilder          pagerBuilder;
    private int                   pageSize;
    private AnalyticsFilter       analyticsFilter;
    private VideoFilter           videoFilter;


    public Pager(PageLoader<T> pageLoader, int pageSize) {
        this.pageLoader = pageLoader;
        this.pageSize = pageSize;
    }

    public Pager(PageLoaderVideos<T> pageLoader, PagerBuilder pagerBuilder, VideoFilter videoFilter) {
        this.pageLoaderVideos = pageLoader;
        this.pagerBuilder = pagerBuilder;
        this.videoFilter = videoFilter;
    }

    public Pager(PageLoaderAnalytic<T> pageLoader, String period, PagerBuilder pagerBuilder, AnalyticsFilter analyticsFilter) {
        this.pageLoaderAnalytic = pageLoader;
        this.period = period;
        this.pagerBuilder = pagerBuilder;
        this.analyticsFilter = analyticsFilter;
    }

    public void next(final PageResponseListener<T> pageResponseListener, ResponseErrorListener errorListener) {
        if (!hasNext) {
            pageResponseListener.onResponse(null);
            return;
        }
        if (pageLoader != null) {
            pageLoader.loadPage(currentPageNum, pageSize, page -> {
                currentPageNum++;

                if (currentPageNum > page.getPagesTotal()) {
                    hasNext = false;
                }

                pageResponseListener.onResponse(page);
            }, errorListener);
        } else if (pageLoaderVideos != null) {
            pageLoaderVideos.loadPage(currentPageNum, pagerBuilder, videoFilter, page -> {
                currentPageNum++;

                if (currentPageNum > page.getPagesTotal()) {
                    hasNext = false;
                }

                pageResponseListener.onResponse(page);
            }, errorListener);

        } else if (pageLoaderAnalytic != null) {
            pageLoaderAnalytic.loadPageOfProjectAnalytics(period, currentPageNum, pagerBuilder, analyticsFilter, page -> {
                currentPageNum++;

                if (currentPageNum > page.getPagesTotal()) {
                    hasNext = false;
                }

                pageResponseListener.onResponse(page);
            }, errorListener);
        }

    }

}
