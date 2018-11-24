package com.kyrylo.kotlinmessenger.latestmessages

import com.kyrylo.kotlinmessenger.latestmessages.view.LatestMessagesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class LatestMessagesFragmentProvider {

    @ContributesAndroidInjector(modules = [LatestMessagesFragmentModule::class])
    internal abstract fun provideLatestMessagesFragment(): LatestMessagesFragment

}