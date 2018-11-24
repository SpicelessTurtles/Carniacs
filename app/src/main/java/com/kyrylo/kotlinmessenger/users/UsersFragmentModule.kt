package com.kyrylo.kotlinmessenger.users

import com.kyrylo.kotlinmessenger.users.interactor.UsersInteractor
import com.kyrylo.kotlinmessenger.users.interactor.UsersMVPInteractor
import com.kyrylo.kotlinmessenger.users.presenter.UsersMVPPresenter
import com.kyrylo.kotlinmessenger.users.presenter.UsersPresenter
import com.kyrylo.kotlinmessenger.users.view.UsersMVPView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.Module
import dagger.Provides

@Module
class UsersFragmentModule {

    @Provides
    internal fun provideUsersInteractor(usersInterator: UsersInteractor): UsersMVPInteractor = usersInterator

    @Provides
    internal fun provideUsersPresenter(usersPresenter: UsersPresenter<UsersMVPView, UsersMVPInteractor>)
            : UsersMVPPresenter<UsersMVPView, UsersMVPInteractor> = usersPresenter

    @Provides
    internal fun provideUsersAdapter(): GroupAdapter<ViewHolder> = GroupAdapter()
}