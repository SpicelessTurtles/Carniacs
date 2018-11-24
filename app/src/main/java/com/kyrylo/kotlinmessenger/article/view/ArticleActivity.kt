package com.kyrylo.kotlinmessenger.article.view

import android.os.Bundle
import android.view.MenuItem
import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.article.interactor.ArticleMVPInteractor
import com.kyrylo.kotlinmessenger.article.presenter.ArticleMVPPresenter
import com.kyrylo.kotlinmessenger.base.view.BaseActivity
import com.kyrylo.kotlinmessenger.data.preferences.model.GoogleNews
import com.kyrylo.kotlinmessenger.database.entities.Task
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import javax.inject.Inject

class ArticleActivity : BaseActivity(), ArticleMVPView {

    @Inject
    internal lateinit var presenter: ArticleMVPPresenter<ArticleMVPView, ArticleMVPInteractor>

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == android.R.id.home) {
            finish()
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setSupportActionBar(toolbar)
        presenter.onAttach(this)
        presenter.onViewPrepared()
    }

    override fun setUp() {
      supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun initializeArticle(article : Task) {
        article.let {
            Picasso.get().load(it.backgroundUrl).fit().into(image)
            article_title.text = it.title
            article_description.text = it.content
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}