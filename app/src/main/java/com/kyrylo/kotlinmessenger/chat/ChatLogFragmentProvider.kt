package com.kyrylo.kotlinmessenger.chat

import com.kyrylo.kotlinmessenger.chat.view.ChatLogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ChatLogFragmentProvider {

    @ContributesAndroidInjector(modules = [ChatLogFragmentModule::class])
    internal abstract fun provideChatLogFragment(): ChatLogFragment

}