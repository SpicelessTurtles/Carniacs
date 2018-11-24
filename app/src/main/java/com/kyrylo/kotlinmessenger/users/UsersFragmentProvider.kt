package com.kyrylo.kotlinmessenger.users


import com.kyrylo.kotlinmessenger.users.view.UsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UsersFragmentProvider {

    @ContributesAndroidInjector(modules = [UsersFragmentModule::class])
    internal abstract fun provideUsersFragment(): UsersFragment

}