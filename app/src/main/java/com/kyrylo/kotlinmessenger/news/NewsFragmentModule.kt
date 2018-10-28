package com.kyrylo.kotlinmessenger.news

import android.support.v7.widget.LinearLayoutManager
import com.kyrylo.kotlinmessenger.news.interactor.NewsInteractor
import com.kyrylo.kotlinmessenger.news.interactor.NewsMVPInteractor
import com.kyrylo.kotlinmessenger.news.presenter.NewsMVPPresenter
import com.kyrylo.kotlinmessenger.news.presenter.NewsPresenter
import com.kyrylo.kotlinmessenger.news.view.NewsAdapter
import com.kyrylo.kotlinmessenger.news.view.NewsFragment
import com.kyrylo.kotlinmessenger.news.view.NewsMVPView
import dagger.Module
import dagger.Provides
import java.util.ArrayList

@Module
class NewsFragmentModule {

    @Provides
    internal fun provideNewsInteractor(interactor: NewsInteractor): NewsMVPInteractor = interactor

    @Provides
    internal fun provideNewsPresenter(presenter: NewsPresenter<NewsMVPView, NewsMVPInteractor>)
            : NewsMVPPresenter<NewsMVPView, NewsMVPInteractor> = presenter

    @Provides
    internal fun provideNewsAdapter(): NewsAdapter = NewsAdapter(ArrayList())

    @Provides
    internal fun provideLinearLayoutManager(fragment: NewsFragment): LinearLayoutManager = LinearLayoutManager(fragment.activity)

}