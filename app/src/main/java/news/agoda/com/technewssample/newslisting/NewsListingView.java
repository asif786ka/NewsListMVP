package news.agoda.com.technewssample.newslisting;


import java.util.List;

import news.agoda.com.technewssample.News;

public interface NewsListingView
{
    void showNews(List<News> aNews);
    void loadingStarted();
    void loadingFailed(String errorMessage);
    void onNewsClicked(News news);
}
