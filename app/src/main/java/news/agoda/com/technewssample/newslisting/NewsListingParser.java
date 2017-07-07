package news.agoda.com.technewssample.newslisting;

import android.support.annotation.NonNull;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import news.agoda.com.technewssample.News;


public class NewsListingParser
{

    public static final String RESULTS = "results";
    public static final String OVERVIEW = "abstract";
    public static final String RELEASE_DATE = "published_date";
    public static final String POSTER_PATH = "multimedia";
    public static final String TITLE = "title";
    public static final String VOTE_AVERAGE = "vote_average";
    private static final String BACKDROP_PATH = "backdrop_path";
    private static final String id = "url";

    @NonNull
    public static List<News> parse(String json) throws JSONException
    {
        List<News> movies = new ArrayList<>();
        JSONObject response = new JSONObject(json);

        if (!response.isNull(RESULTS))
        {
            JSONArray results = response.getJSONArray(RESULTS);

            for (int i = 0; i < results.length(); i++)
            {
                movies.add(getNews(results.getJSONObject(i)));
            }

        } else
        {
            // No results
        }

        return movies;
    }

    @NonNull
    private static News getNews(JSONObject result) throws JSONException
    {
        News news = new News();

        if (!result.isNull(id))
        {
            news.setId(result.getString(id));
        }

        if (!result.isNull(OVERVIEW))
        {
            news.setOverview(result.getString(OVERVIEW));
        }

        if (!result.isNull(RELEASE_DATE))
        {
            news.setReleaseDate(String.valueOf(result.get(RELEASE_DATE)));
        }

        if (!result.isNull(POSTER_PATH)&& !result.getString(POSTER_PATH).equalsIgnoreCase(""))
        {
           /* if(result.getJSONArray(POSTER_PATH).getJSONObject(0)!=null) {
                String url = result.getJSONArray(POSTER_PATH).getJSONObject(0).getString("url");
                news.setPosterPath(url);
            }*/
           JSONArray multi = result.getJSONArray(POSTER_PATH);
            if(multi.length()>1) {
                String url = multi.getJSONObject(1).getString("url");
                news.setPosterPath(url);
            }
        }

        if (!result.isNull(POSTER_PATH)&& !result.getString(POSTER_PATH).equalsIgnoreCase(""))        {
            JSONArray multi = result.getJSONArray(POSTER_PATH);
            if(multi.length()>1) {
                String url = multi.getJSONObject(2).getString("url");
                news.setNewsDetailPath(url);
            }
        }

        if (!result.isNull(TITLE))
        {
            news.setTitle(result.getString(TITLE));
        }

        if (!result.isNull(VOTE_AVERAGE))
        {
            news.setVoteAverage(result.getDouble(VOTE_AVERAGE));
        }

        return news;
    }
}
