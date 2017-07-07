package news.agoda.com.technewssample.newslisting;


import java.util.List;

import news.agoda.com.technewssample.News;
import rx.Observable;

public interface NewsListingInteractor
{
    Observable<List<News>> fetchNews();
}
