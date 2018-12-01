package com.kyrylo.kotlinmessenger.profile

import com.kyrylo.kotlinmessenger.news.NewsFragmentModule
import com.kyrylo.kotlinmessenger.news.view.NewsFragment
import com.kyrylo.kotlinmessenger.profile.view.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProfileFragmentProvider {

    @ContributesAndroidInjector(modules = [ProfileFragmentModule::class])
    internal abstract fun provideProfileFragment(): ProfileFragment

}