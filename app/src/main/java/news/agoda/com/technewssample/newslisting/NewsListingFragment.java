package news.agoda.com.technewssample.newslisting;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import news.agoda.com.technewssample.BaseApplication;
import news.agoda.com.technewssample.News;
import news.agoda.com.technewssample.R;

public class NewsListingFragment extends Fragment implements NewsListingView
{
    @Inject
    NewsListingPresenter newsPresenter;

    @Bind(R.id.news_listing)
    RecyclerView newsListing;

    private RecyclerView.Adapter adapter;
    private List<News> news = new ArrayList<>();
    private Callback callback;

    public NewsListingFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        ((BaseApplication) getActivity().getApplication()).createListingComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, rootView);
        initLayoutReferences();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        newsPresenter.setView(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_news:
        }

        return super.onOptionsItemSelected(item);
    }



    private void initLayoutReferences()
    {
        newsListing.setHasFixedSize(true);

        int columns;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            columns = 3;
        } else
        {
            columns = getResources().getInteger(R.integer.no_of_columns);
        }
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), columns);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        newsListing.setLayoutManager(layoutManager);
        adapter = new NewsListingAdapter(news, this);
        newsListing.setAdapter(adapter);
    }

    @Override
    public void showNews(List<News> aNews)
    {
        this.news.clear();
        this.news.addAll(aNews);
        newsListing.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        callback.onNewsLoaded(aNews.get(0));
    }

    @Override
    public void loadingStarted()
    {
        Snackbar.make(newsListing, R.string.loading_news, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loadingFailed(String errorMessage)
    {
        Snackbar.make(newsListing, errorMessage, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void onNewsClicked(News news)
    {
        callback.onNewsClicked(news);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        newsPresenter.destroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDetach()
    {
        callback = null;
        super.onDetach();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        ((BaseApplication) getActivity().getApplication()).releaseListingComponent();
    }

    public interface Callback
    {
        void onNewsLoaded(News news);

        void onNewsClicked(News news);
    }
}
