package com.kyrylo.kotlinmessenger.di.module

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kyrylo.kotlinmessenger.data.preferences.AppPreferenceHelper
import com.kyrylo.kotlinmessenger.data.preferences.PreferenceHelper
import com.kyrylo.kotlinmessenger.data.preferences.network.ApiHelper
import com.kyrylo.kotlinmessenger.data.preferences.network.AppApiHelper
import com.kyrylo.kotlinmessenger.di.PreferenceInfo
import com.kyrylo.kotlinmessenger.di.UserUID
import com.kyrylo.kotlinmessenger.utilities.AppConstants

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton
import com.kyrylo.kotlinmessenger.utilities.SchedulerProvider
import java.lang.Exception
import javax.inject.Named

/**
 * Created by jyotidubey on 05/01/18.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @PreferenceInfo
    internal fun provideprefFileName(): String = AppConstants.PREF_NAME

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

    @Provides
    @Singleton
    internal fun providePrefHelper(appPreferenceHelper: AppPreferenceHelper): PreferenceHelper = appPreferenceHelper

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper

    @Provides
    @Named("LatestMessages")
    internal fun provideQueryMessage(@UserUID userUID: String) : DatabaseReference =
            FirebaseDatabase.getInstance().getReference("/latest-messages/$userUID")


    @Provides
    @Named("CurrentUser")
    internal fun provideCurrentUser(@UserUID userUID: String) : DatabaseReference =
            FirebaseDatabase.getInstance().getReference("/users/$userUID")


    @Provides
    @Named("UserList")
    internal fun provideUserList() : DatabaseReference =
            FirebaseDatabase.getInstance().getReference("/users")



    @Provides
    @UserUID
    internal fun provideUserUID() : String {
        if(FirebaseAuth.getInstance().currentUser != null) {
            return FirebaseAuth.getInstance().currentUser!!.uid
        }
        else{
            return ""
        }
    }


}