package news.agoda.com.technewssample.newsdetails;

import dagger.Subcomponent;

@DetailsScope
@Subcomponent(modules = {DetailsModule.class})
public interface DetailsComponent
{
    void inject(NewsDetailsFragment target);
}
