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
import android.view.View
import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.R.id.*
import com.kyrylo.kotlinmessenger.base.view.BaseActivity
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

import javax.inject.Inject

class MainActivity : BaseActivity(), MainMVPView, NavigationView.OnNavigationItemSelectedListener, HasSupportFragmentInjector {

    @Inject
    internal lateinit var presenter: MainMVPPresenter<MainMVPView, MainMVPInteractor>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_main)
        setUpDrawerMenu()
        //setupCardContainerView()
        presenter.onAttach(this)
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        }
       //  val fragment = supportFragmentManager.findFragmentByTag(NewsFragment.TAG)

        super.onBackPressed()
       // fragment?.let { onFragmentDetached() } ?: super.onBackPressed()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onFragmentDetached(tag: String) {
        supportFragmentManager?.removeFragment(tag = tag)
        unlockDrawer()
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

    override fun openNewsFragment() {
        supportFragmentManager.addFragment(R.id.cl_root_view, NewsFragment.newInstance(), NewsFragment.TAG)
    }

    override fun openFeedActivity() {
        ///   val intent = Intent(this, FeedActivity::class.java)
        //   startActivity(intent)
    }

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

        return super.onOptionsItemSelected(item)
    }
}
