package com.kyrylo.kotlinmessenger.news.view

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.base.view.BaseFragment
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.kyrylo.kotlinmessenger.data.preferences.model.GoogleNews
import com.kyrylo.kotlinmessenger.news.interactor.NewsMVPInteractor
import com.kyrylo.kotlinmessenger.news.presenter.NewsMVPPresenter
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ViewListener
import kotlinx.android.synthetic.main.carousel_item_list.*
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject


class NewsFragment : BaseFragment(), NewsMVPView {

    @Inject
    internal lateinit var newsAdapter: NewsAdapter
    @Inject
    internal lateinit var layoutManager: LinearLayoutManager
    @Inject
    internal lateinit var presenter: NewsMVPPresenter<NewsMVPView, NewsMVPInteractor>


    companion object {

        internal val TAG = "NewsFragments"

        fun newInstance(): NewsFragment {
            return NewsFragment()
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_news, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun displayNewsList(news: List<GoogleNews>?)= news?.let {
        val carouselView = CustomCarouselView(context)
        newsAdapter.addNewsToList(it, carouselView)
    }

    override fun setUp() {
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        blog_recycler_view.layoutManager = layoutManager
        blog_recycler_view.itemAnimator = DefaultItemAnimator()
        blog_recycler_view.adapter = newsAdapter

        presenter.onViewPrepared()
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

}