package com.kyrylo.kotlinmessenger.main.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.base.view.BaseActivity
import com.kyrylo.kotlinmessenger.chat.view.ChatLogFragment
import com.kyrylo.kotlinmessenger.latestmessages.view.LatestMessagesFragment
import com.kyrylo.kotlinmessenger.login.view.LoginActivity
import com.kyrylo.kotlinmessenger.main.interactor.MainMVPInteractor
import com.kyrylo.kotlinmessenger.main.presenter.MainMVPPresenter
import com.kyrylo.kotlinmessenger.news.view.NewsFragment
import com.kyrylo.kotlinmessenger.utilities.extension.addFragment
import com.kyrylo.kotlinmessenger.utilities.extension.removeFragment

import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main_main.*
import kotlinx.android.synthetic.main.app_bar_navigation.*
import java.util.*

import javax.inject.Inject
import android.os.Build
import android.util.Log
import com.kyrylo.kotlinmessenger.article.view.ArticleActivity
import com.kyrylo.kotlinmessenger.users.view.UsersFragment


class MainActivity : BaseActivity(), MainMVPView, NavigationView.OnNavigationItemSelectedListener, HasSupportFragmentInjector {

    @Inject
    internal lateinit var presenter: MainMVPPresenter<MainMVPView, MainMVPInteractor>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    //  var currentFragmentTag : String = NewsFragment.TAG//TODO: change logic of fragment transaction
    var currentFragmentTag: LinkedList<String> = LinkedList()//TODO:WHAT ABOUT POPBACKSTUCK AH, BOY?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_main)
        setUpDrawerMenu()
        //setupCardContainerView()
        presenter.onAttach(this)
    }

    override fun onBackPressed() {//TODO:WHAT ABOUT POPBACKSTUCK AH, BOY?
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        }

        val fragmentManager = supportFragmentManager
        val isStateSaved = fragmentManager.isStateSaved
        if (isStateSaved && Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) {
            // Older versions will throw an exception from the framework
            // FragmentManager.popBackStackImmediate(), so we'll just
            // return here. The Activity is likely already on its way out
            // since the fragmentManager has already been saved.
            return
        }
        if (isStateSaved || !fragmentManager.popBackStackImmediate()) {
            super.onBackPressed()
        }

        // val fragment = supportFragmentManager.findFragmentByTag(NewsFragment.TAG)

        //  if (currentFragmentTag.last.equals(NewsFragment.TAG) && currentFragmentTag.size == 1) super.onBackPressed()

        // onFragmentDetached(currentFragmentTag.last)
        //  fragment?.let { onFragmentDetached(currentFragmentTag.last) }

       // currentFragmentTag.pop()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onFragmentDetached(tag: String) {
        supportFragmentManager?.removeFragment(tag = tag)
        //  unlockDrawer()
    }

    override fun onFragmentAttached() {
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        /* when (item.itemId) {
             R.id.navItemAbout -> {
                 presenter.onDrawerOptionAboutClick()
             }
             R.id.navItemRateUs -> {
                 presenter.onDrawerOptionRateUsClick()
             }
             R.id.navItemFeed -> {
                 presenter.onDrawerOptionFeedClick()
             }
             R.id.navItemLogout -> {
                 presenter.onDrawerOptionLogoutClick()
             }
         }
         drawerLayout.closeDrawer(GravityCompat.START)
         */
        return true
    }

    override fun lockDrawer() = drawer?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

    override fun unlockDrawer() = drawer?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)


    //override fun inflateUserDetails(userDetails: Pair<String?, String?>) {
    //  navView.getHeaderView(0).nav_name.text = userDetails.first
    //   navView.getHeaderView(0).nav_email.text = userDetails.second
    // }

    override fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun openArticleActivity() {
        val intent = Intent(this, ArticleActivity::class.java)
        startActivity(intent)
    }

    override fun openNewsFragment() =//TODO:WHAT ABOUT POPBACKSTUCK AH, BOY?
        supportFragmentManager.addFragment(R.id.cl_root_view, NewsFragment.newInstance(), NewsFragment.TAG)


    override fun openLatestMessagesFragment() =//TODO:WHAT ABOUT POPBACKSTUCK AH, BOY?
        supportFragmentManager.addFragment(R.id.cl_root_view, LatestMessagesFragment.newInstance(), LatestMessagesFragment.TAG)


    override fun openChatFragment() =//TODO:WHAT ABOUT POPBACKSTUCK AH, BOY?
        supportFragmentManager.addFragment(R.id.cl_root_view, ChatLogFragment.newInstance(), ChatLogFragment.TAG)


    override fun openUsersFragment() =
            supportFragmentManager.addFragment(R.id.cl_root_view, UsersFragment.newInstance(), UsersFragment.TAG)


    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    private fun setUpDrawerMenu() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.getItemId()

        when (id) {
            R.id.menu_new_message ->
                presenter.onLatestMessagesClick()

            R.id.menu_users ->
                presenter.onNewMessageClick()
        }

        return super.onOptionsItemSelected(item)
    }
}
