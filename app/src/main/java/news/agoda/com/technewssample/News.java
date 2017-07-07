package news.agoda.com.technewssample;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable
{
    private String id;
    private String overview;
    private String releaseDate;
    private String posterPath;
    private String newsDetailPath;
    private String title;
    private double voteAverage;

    public News()
    {

    }

    protected News(Parcel in)
    {
        id = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        posterPath = in.readString();
        newsDetailPath = in.readString();
        title = in.readString();
        voteAverage = in.readDouble();
    }

    public static final Creator<News> CREATOR = new Creator<News>()
    {
        @Override
        public News createFromParcel(Parcel in)
        {
            return new News(in);
        }

        @Override
        public News[] newArray(int size)
        {
            return new News[size];
        }
    };

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getOverview()
    {
        return overview;
    }

    public void setOverview(String overview)
    {
        this.overview = overview;
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath()
    {
        return posterPath;
    }

    public void setPosterPath(String posterPath)
    {
        this.posterPath = posterPath;
    }

    public String getNewsDetailPath()
    {
        return newsDetailPath;
    }

    public void setNewsDetailPath(String newsDetailPath)
    {
        this.newsDetailPath = newsDetailPath;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public double getVoteAverage()
    {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage)
    {
        this.voteAverage = voteAverage;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(id);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
        parcel.writeString(posterPath);
        parcel.writeString(newsDetailPath);
        parcel.writeString(title);
        parcel.writeDouble(voteAverage);
    }
}
