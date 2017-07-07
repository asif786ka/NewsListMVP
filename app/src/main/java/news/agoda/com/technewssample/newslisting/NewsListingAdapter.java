package news.agoda.com.technewssample.newslisting;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;


import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import news.agoda.com.technewssample.News;
import news.agoda.com.technewssample.R;

public class NewsListingAdapter extends RecyclerView.Adapter<NewsListingAdapter.ViewHolder>
{
    private List<News> aNews;
    private Context context;
    private NewsListingView view;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        @Bind(R.id.news_item_poster)
        ImageView poster;
        @Bind(R.id.title_background)
        View titleBackground;
        @Bind(R.id.news_name)
        TextView name;

        public News news;

        public ViewHolder(View root)
        {
            super(root);
            ButterKnife.bind(this, root);
        }

        @Override
        public void onClick(View view)
        {
            NewsListingAdapter.this.view.onNewsClicked(news);
        }
    }

    public NewsListingAdapter(List<News> aNews, NewsListingView newsView)
    {
        this.aNews = aNews;
        view = newsView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.news_grid_item, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        holder.itemView.setOnClickListener(holder);
        holder.news = aNews.get(position);
        holder.name.setText(holder.news.getTitle());
        Glide.with(context).load(holder.news
                .getPosterPath()).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(new BitmapImageViewTarget(holder.poster)
                {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim)
                    {
                        super.onResourceReady(bitmap, anim);

                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener()
                        {
                            @Override
                            public void onGenerated(Palette palette)
                            {
                                holder.titleBackground.setBackgroundColor(palette.getVibrantColor(context
                                        .getResources().getColor(R.color.black_translucent_60)));
                            }
                        });
                    }
                });
    }

    @Override
    public int getItemCount()
    {
        return aNews.size();
    }
}
