package com.kyrylo.kotlinmessenger.di.builder

import com.kyrylo.kotlinmessenger.article.ArticleActivityModule
import com.kyrylo.kotlinmessenger.article.view.ArticleActivity
import com.kyrylo.kotlinmessenger.chat.ChatLogFragmentProvider
import com.kyrylo.kotlinmessenger.latestmessages.LatestMessagesFragmentProvider
import com.kyrylo.kotlinmessenger.login.LoginActivityModule
import com.kyrylo.kotlinmessenger.login.view.LoginActivity
import com.kyrylo.kotlinmessenger.main.view.MainActivity
import com.kyrylo.kotlinmessenger.news.NewsFragmentProvider
import com.kyrylo.kotlinmessenger.profile.ProfileFragmentProvider
import com.kyrylo.kotlinmessenger.register.RegisterActivityModule
import com.kyrylo.kotlinmessenger.register.view.RegisterActivity
import com.kyrylo.kotlinmessenger.splash.SplashActivityModule
import com.kyrylo.kotlinmessenger.splash.view.SplashActivity
import com.kyrylo.kotlinmessenger.users.UsersFragmentProvider
import com.mindorks.framework.mvp.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by jyotidubey on 05/01/18.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(SplashActivityModule::class)])
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [(RegisterActivityModule::class)])
    abstract fun bindRegisterActivity(): RegisterActivity

    @ContributesAndroidInjector(modules = [(LoginActivityModule::class)])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [(ArticleActivityModule::class)])
    abstract fun bindArticleActivity(): ArticleActivity

    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (NewsFragmentProvider::class), (LatestMessagesFragmentProvider::class), (ChatLogFragmentProvider::class), (UsersFragmentProvider::class), (ProfileFragmentProvider::class)])
    abstract fun bindMainActivity(): MainActivity

}