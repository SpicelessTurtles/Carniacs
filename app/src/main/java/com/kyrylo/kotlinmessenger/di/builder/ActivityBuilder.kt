package com.kyrylo.kotlinmessenger.di.builder

import com.kyrylo.kotlinmessenger.latestmessages.view.LatestMessagesActivity
import com.kyrylo.kotlinmessenger.latestmessages.LatestMessagesModule
import com.kyrylo.kotlinmessenger.main.view.MainActivity
import com.kyrylo.kotlinmessenger.news.NewsFragmentProvider
import com.kyrylo.kotlinmessenger.register.RegisterActivityModule
import com.kyrylo.kotlinmessenger.register.view.RegisterActivity
import com.kyrylo.kotlinmessenger.splash.SplashActivityModule
import com.kyrylo.kotlinmessenger.splash.view.SplashMVPActivity
import com.mindorks.framework.mvp.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by jyotidubey on 05/01/18.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(SplashActivityModule::class)])
    abstract fun bindSplashActivity(): SplashMVPActivity

    @ContributesAndroidInjector(modules = [(RegisterActivityModule::class)])
    abstract fun bindRegisterActivity(): RegisterActivity

    @ContributesAndroidInjector(modules = [(LatestMessagesModule::class)])
    abstract fun bindLatestMessagesActivity(): LatestMessagesActivity

    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (NewsFragmentProvider::class)])
    abstract fun bindMainActivity(): MainActivity

//
//    @ContributesAndroidInjector(modules = [(LoginActivityModule::class)])
//    abstract fun bindLoginActivity(): LoginActivity
//
//    @ContributesAndroidInjector(modules = [(BlogFragmentProvider::class), (OpenSourceFragmentProvider::class)])
//    abstract fun bindFeedActivity(): FeedActivity

}