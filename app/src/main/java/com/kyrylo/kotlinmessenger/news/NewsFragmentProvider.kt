package com.kyrylo.kotlinmessenger.news

import com.kyrylo.kotlinmessenger.news.view.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NewsFragmentProvider {

    @ContributesAndroidInjector(modules = [NewsFragmentModule::class])
    internal abstract fun provideAboutFragment(): NewsFragment

}