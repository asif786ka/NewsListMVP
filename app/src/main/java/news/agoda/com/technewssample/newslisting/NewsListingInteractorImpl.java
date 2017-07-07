package news.agoda.com.technewssample.newslisting;

import android.support.annotation.NonNull;



import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import news.agoda.com.technewssample.Api;
import news.agoda.com.technewssample.News;

import news.agoda.com.technewssample.network.RequestGenerator;
import news.agoda.com.technewssample.network.RequestHandler;
import okhttp3.Request;
import rx.Observable;


public class NewsListingInteractorImpl implements NewsListingInteractor
{
    private RequestHandler requestHandler;

    NewsListingInteractorImpl(RequestHandler requestHandler)
    {
        this.requestHandler = requestHandler;
    }

    @Override
    public Observable<List<News>> fetchNews()
    {
        return Observable.fromCallable(this::getMovieList);
    }


    private List<News> getMovieList() throws IOException, JSONException
    {
        return fetchMovieList(Api.GET_NEWS);
    }

    @NonNull
    private List<News> fetchMovieList(String url) throws IOException, JSONException
    {
        Request request = RequestGenerator.get(url);
        String response = requestHandler.request(request);
        return NewsListingParser.parse(response);
    }
}
